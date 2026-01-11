package ru.teamscore.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.teamscore.common.entity.RawSensorData;
import ru.teamscore.common.utils.HibernateUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsumerApp {
    private static LocalDateTime lastProcessedTime = null;
    private static final RawMessageRepository repository = new RawMessageRepository();
    private static final MessageProcessor processor = new MessageProcessor();

    public static void main(String[] args) {
        System.out.println("Consumer запущен");
        try {
            while (true) {
                System.out.println("Проверка новых сообщений");

                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                    List<RawSensorData> newMessages = repository.findNewMessages(session, lastProcessedTime);

                    if (newMessages.isEmpty()) {
                        System.out.println("Новых сообщений нет. Жду 5 секунд...");
                        Thread.sleep(5000);
                        continue;
                    }

                    System.out.println("Найдено " + newMessages.size() + " новых сообщений");

                    int processedCount = processMessages(session, newMessages);
                    updateLastProcessedTime(newMessages);


                } catch (Exception e) {
                    System.err.println("Ошибка: " + e.getMessage());
                    e.printStackTrace();
                    Thread.sleep(10000);
                }
            }

        } catch (InterruptedException e) {
            System.out.println("\nConsumer остановлен");
        } finally {
            HibernateUtil.shutdown();
        }
    }

    private static int processMessages(Session session, List<RawSensorData> messages)
            throws JsonProcessingException {

        Transaction tx = session.beginTransaction();
        List<Long> processedIds = new ArrayList<>();

        try {
            for (RawSensorData rawData : messages) {
                System.out.printf("Обработка: %s -> %s%n",
                        rawData.getSensor().getDevice().getName(),
                        rawData.getSensor().getType());

                processor.processRawData(rawData, session);
                processedIds.add(rawData.getId());
            }

            repository.deleteProcessedMessages(session, processedIds);
            tx.commit();
            return messages.size();

        } catch (Exception e) {
            tx.rollback();
            System.err.println("Ошибка обработки " + e.getMessage());
            throw e;
        }
    }

    private static void updateLastProcessedTime(List<RawSensorData> messages) {
        if (!messages.isEmpty()) {
            RawSensorData lastMessage = messages.get(messages.size() - 1);
            lastProcessedTime = lastMessage.getSavedAt();
        }
    }
}