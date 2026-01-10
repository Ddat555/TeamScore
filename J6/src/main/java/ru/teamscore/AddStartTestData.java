package ru.teamscore;

import ru.teamscore.common.SensorType;
import ru.teamscore.common.entity.Device;
import ru.teamscore.producer.DeviceManager;

public class AddStartTestData {

    public static void main(String[] args) {
        DeviceManager deviceManager = new DeviceManager();

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
    }
}
