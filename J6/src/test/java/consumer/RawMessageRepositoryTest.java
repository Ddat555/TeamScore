package consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.teamscore.common.SensorType;
import ru.teamscore.common.entity.Device;
import ru.teamscore.common.entity.RawSensorData;
import ru.teamscore.common.utils.HibernateUtil;
import ru.teamscore.common.utils.TestHibernateUtil;
import ru.teamscore.consumer.RawMessageRepository;
import ru.teamscore.producer.DeviceManager;
import ru.teamscore.producer.SensorDataGenerator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RawMessageRepositoryTest {

    @BeforeAll
    static void setTestData() throws JsonProcessingException {
        DeviceManager deviceManager = new DeviceManager(TestHibernateUtil.getSessionFactory());

        System.out.println("Инициализация тестовыми устройствами и датчиками...");

        Device[] testDevices = {
                deviceManager.addDevice("Dev1"),
                deviceManager.addDevice("Dev2"),
                deviceManager.addDevice("Dev3"),
                deviceManager.addDevice("Dev4"),
                deviceManager.addDevice("Dev5"),
                deviceManager.addDevice("Dev6")
        };

        deviceManager.addSensor(testDevices[0], SensorType.LIGHT);
        deviceManager.addSensor(testDevices[0], SensorType.BAROMETER);

        deviceManager.addSensor(testDevices[1], SensorType.LIGHT);
        deviceManager.addSensor(testDevices[1], SensorType.BAROMETER);
        deviceManager.addSensor(testDevices[1], SensorType.LOCATION);
        deviceManager.addSensor(testDevices[1], SensorType.ACCELEROMETER);

        deviceManager.addSensor(testDevices[2], SensorType.LIGHT);
        deviceManager.addSensor(testDevices[2], SensorType.ACCELEROMETER);

        deviceManager.addSensor(testDevices[3], SensorType.LIGHT);
        deviceManager.addSensor(testDevices[4], SensorType.BAROMETER);
        deviceManager.addSensor(testDevices[5], SensorType.LOCATION);

        System.out.println("Создано " + deviceManager.getDeviceSize() + " устройств с датчиками");


        SensorDataGenerator generator = new SensorDataGenerator(deviceManager);


        try (Session session = TestHibernateUtil.getSessionFactory().openSession()){
            for (int i = 0; i < 10; i++) {
                RawSensorData data = generator.generateRandomData();
                Transaction tx = session.beginTransaction();
                session.save(data);
                tx.commit();
            }

        }
    }

    @Test
    void testReadData() {
        RawMessageRepository rawMessageRepository = new RawMessageRepository();
        try (Session session = TestHibernateUtil.getSessionFactory().openSession()) {
            List<RawSensorData> allMessages = rawMessageRepository.findNewMessages(session, null);
            System.out.println("Получено: " + allMessages.size() + " сообщений");
            assertNotNull(allMessages);
            assertInstanceOf(List.class, allMessages);
            assertEquals(10, allMessages.size());
            for (RawSensorData message : allMessages) {
                assertInstanceOf(RawSensorData.class, message);
                assertNotNull(message.getId());
                assertNotNull(message.getValue());
                assertNotNull(message.getSensor());
            }
        }
    }
}
