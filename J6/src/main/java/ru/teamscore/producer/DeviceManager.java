package ru.teamscore.producer;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.teamscore.common.SensorType;
import ru.teamscore.common.entity.Device;
import ru.teamscore.common.entity.Sensor;
import ru.teamscore.common.utils.HibernateUtil;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class DeviceManager {
    private List<Device> devices;
    private final Random rnd = new Random();

    public DeviceManager() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Загружаем все устройства с датчиками
            devices = session.createQuery(
                    "SELECT DISTINCT d FROM Device d LEFT JOIN FETCH d.sensors",
                    Device.class
            ).getResultList();

            System.out.println("Загружено " + devices.size() + " устройств из БД");
        }
    }

    public Device getRandomDevice(){
        if (devices.isEmpty()) {
            return null;
        }
        return devices.get(rnd.nextInt(devices.size()));
    }

    public Sensor getRandomSensor(Device device) {
        if (device == null || device.getSensors().isEmpty()) {
            return null;
        }
        List<Sensor> sensors = device.getSensors();
        return sensors.get(rnd.nextInt(sensors.size()));
    }

    public Device addDevice(String name) {
        for (Device device : devices) {
            if (device.getName().equals(name)) {
                return device;
            }
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Device device = new Device(name);
            session.save(device);
            tx.commit();

            devices.add(device);
            return device;
        }
    }

    public Sensor addSensor(Device device, SensorType type) {
        String sensorId = UUID.randomUUID().toString();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Sensor sensor = new Sensor(sensorId, type);
            sensor.setDevice(device);
            device.addSensor(sensor);

            session.update(device);
            session.save(sensor);
            tx.commit();

            return sensor;
        }
    }

    public int getDeviceSize(){
        return devices.size();
    }
}
