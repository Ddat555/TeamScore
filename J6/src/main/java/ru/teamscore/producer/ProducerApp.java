package ru.teamscore.producer;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.teamscore.common.utils.HibernateUtil;
import ru.teamscore.common.entity.RawSensorData;

public class ProducerApp {

    public static void main(String[] args) {
        System.out.println("Запуск Producer");

        try {
            DeviceManager deviceManager = new DeviceManager(HibernateUtil.getSessionFactory());
            if (deviceManager.getDeviceSize() == 0) {
                System.out.println("В БД нет устройств. Запустите AddStartTestData для создания тестовых данных.");
                return;
            }

            SensorDataGenerator generator = new SensorDataGenerator(deviceManager);

            System.out.println("Начинаю генерацию сообщений");
            int messageCount = 0;

            while (true) {
                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                    RawSensorData data = generator.generateRandomData();

                    Transaction tx = session.beginTransaction();
                    session.save(data);
                    tx.commit();

                    messageCount++;
                    System.out.printf("[%d] %s -> %s (измерено: %s)\n",
                            messageCount,
                            data.getSensor().getDevice().getName(),
                            data.getSensor().getType(),
                            data.getMeasuredAt());

                } catch (Exception e) {
                    System.err.println("Ошибка при сохранении: " + e.getMessage());
                }


                int delay = 500 + (int)(Math.random() * 2500);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    break;
                }

                // для бесконечного генерирования надо удалить. не забыть
                if (messageCount >= 100) {
                    System.out.println("Сгенерировано 100 сообщений. Остановка.");
                    break;
                }
                //-------------------------------------
            }

        } catch (Exception e) {
            System.err.println("Ошибка в Producer: " + e.getMessage());
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
            System.out.println("Producer завершил работу");
        }
    }
}
