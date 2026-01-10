package ru.teamscore.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.teamscore.common.SensorType;
import ru.teamscore.common.entity.RawSensorData;
import ru.teamscore.common.entity.sensorsDataEntity.AccelerometerData;
import ru.teamscore.common.entity.sensorsDataEntity.BarometerData;
import ru.teamscore.common.entity.sensorsDataEntity.LightData;
import ru.teamscore.common.entity.sensorsDataEntity.LocationData;
import ru.teamscore.common.utils.HibernateUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConsumerApp {

    private static LocalDateTime lastProcessedTime = null;

    public static void main(String[] args) {
        System.out.println("Запуск Consumer");

        try {
            while (true) {
                System.out.println("Проверка новых сообщений...");

                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                    List<RawSensorData> newMessages = findNewMessages(session);

                    if (newMessages.isEmpty()) {
                        System.out.println("Новых сообщений нет. Жду 5 секунд...");
                        Thread.sleep(5000);
                        continue;
                    }

                    System.out.println("Найдено " + newMessages.size() + " новых сообщений");

                    processMessages(session, newMessages);
                    updateLastProcessedTime(newMessages);

                } catch (Exception e) {
                    System.err.println("Ошибка в Consumer: " + e.getMessage());
                    e.printStackTrace();
                    Thread.sleep(10000);
                }
            }

        } catch (InterruptedException e) {
            System.out.println("Consumer остановлен");
        } finally {
            HibernateUtil.shutdown();
            System.out.println("=== Consumer завершил работу ===");
        }
    }

    private static List<RawSensorData> findNewMessages(Session session) {
        if (lastProcessedTime == null) {
            String hql = "FROM RawSensorData r ORDER BY r.savedAt ASC";
            return session.createQuery(hql, RawSensorData.class)
                    .setMaxResults(20)
                    .getResultList();
        } else {
            String hql = "FROM RawSensorData r WHERE r.savedAt > :lastTime ORDER BY r.savedAt ASC";
            return session.createQuery(hql, RawSensorData.class)
                    .setParameter("lastTime", lastProcessedTime)
                    .setMaxResults(20)
                    .getResultList();
        }
    }

    private static void processMessages(Session session, List<RawSensorData> messages)
            throws JsonProcessingException {

        Transaction tx = session.beginTransaction();
        ObjectMapper mapper = new ObjectMapper();
        List<Long> processedMessage = new ArrayList<>();

        try {
            for (RawSensorData rawData : messages) {
                System.out.printf("Обработка: %s -> %s\n",
                        rawData.getSensor().getDevice().getName(),
                        rawData.getSensor().getType());

                SensorType type = rawData.getSensor().getType();
                Map<String, Object> res = mapper.readValue(rawData.getValue(), Map.class);

                switch (type) {
                    case LIGHT:
                        int lightValue = ((Number) res.get("light")).intValue();
                        LightData lightData = new LightData(
                                rawData.getSensor(),
                                rawData.getMeasuredAt(),
                                lightValue);
                        session.save(lightData);
                        processedMessage.add(rawData.getId());
                        break;

                    case LOCATION:
                        double longitude = ((Number) res.get("longitude")).doubleValue();
                        double latitude = ((Number) res.get("latitude")).doubleValue();
                        LocationData locationData = new LocationData(
                                rawData.getSensor(),
                                rawData.getMeasuredAt(),
                                longitude,
                                latitude
                        );
                        session.save(locationData);
                        processedMessage.add(rawData.getId());
                        break;

                    case BAROMETER:
                        double pressure = ((Number) res.get("air_pressure")).doubleValue();
                        BarometerData barometerData = new BarometerData(
                                rawData.getSensor(),
                                rawData.getMeasuredAt(),
                                pressure
                        );
                        session.save(barometerData);
                        processedMessage.add(rawData.getId());
                        break;

                    case ACCELEROMETER:
                        double x = ((Number) res.get("x")).doubleValue();
                        double y = ((Number) res.get("y")).doubleValue();
                        double z = ((Number) res.get("z")).doubleValue();
                        AccelerometerData accelerometerData = new AccelerometerData(
                                rawData.getSensor(),
                                rawData.getMeasuredAt(),
                                x, y, z
                        );
                        session.save(accelerometerData);
                        processedMessage.add(rawData.getId());
                        break;
                }

            }
            deleteProcessedMessage(session,processedMessage);
            tx.commit();
            System.out.println("Успешно обработано " + messages.size() + " сообщений");

        } catch (Exception e) {
            tx.rollback();
            System.err.println("Ошибка обработки, транзакция откатана: " + e.getMessage());
            throw e;
        }
    }

    private static void updateLastProcessedTime(List<RawSensorData> messages) {
        if (!messages.isEmpty()) {
            RawSensorData lastMessage = messages.get(messages.size() - 1);
            lastProcessedTime = lastMessage.getSavedAt();
        }
    }

    private static void deleteProcessedMessage(Session session, List<Long> rawSensorDataList) {
        String hql = "DELETE FROM RawSensorData r WHERE r.id IN :ids";
        int deleted = session.createQuery(hql)
                .setParameterList("ids", rawSensorDataList)
                .executeUpdate();
    }
}
