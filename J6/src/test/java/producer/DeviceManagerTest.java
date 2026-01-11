package producer;

import org.junit.jupiter.api.Test;
import ru.teamscore.common.SensorType;
import ru.teamscore.common.utils.TestHibernateUtil;
import ru.teamscore.producer.DeviceManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeviceManagerTest {

    @Test
    void addNewDeviceAndSensorTest() {

        DeviceManager deviceManager = new DeviceManager(TestHibernateUtil.getSessionFactory());

        int size = deviceManager.getDeviceSize();
        var newDevice = deviceManager.addDevice("TestDevice");
        assertEquals(size + 1, deviceManager.getDeviceSize());
        deviceManager.addSensor(newDevice, SensorType.LIGHT);
        assertEquals(1, newDevice.getSensors().size());
    }
}
