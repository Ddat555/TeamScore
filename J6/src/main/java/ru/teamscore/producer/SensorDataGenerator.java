package ru.teamscore.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.teamscore.common.SensorType;
import ru.teamscore.common.entity.RawSensorData;
import ru.teamscore.common.entity.Sensor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SensorDataGenerator {
    private final DeviceManager deviceManager;
    private final Random rnd = new Random();

    public SensorDataGenerator(DeviceManager deviceManager) {
        this.deviceManager = deviceManager;
    }

    public RawSensorData generateRandomData() throws JsonProcessingException {
        var device = deviceManager.getRandomDevice();
        if (device == null) {
            throw new IllegalStateException("Нет устройств в БД");
        }

        Sensor sensor = deviceManager.getRandomSensor(device);
        if (sensor == null) {
            throw new IllegalStateException("У устройства нет датчиков");
        }

        Object value = generateSensorValue(sensor.getType());

        LocalDateTime measuredAt = LocalDateTime.now()
                .minusMinutes(rnd.nextInt(10))
                .minusSeconds(rnd.nextInt(60));

        return new RawSensorData(sensor, measuredAt, value);
    }

    private Object generateSensorValue(SensorType type) {
        Map<String, Object> valueMap = new HashMap<>();

        switch (type) {
            case LIGHT:
                valueMap.put("light", rnd.nextInt(1024));
                break;

            case BAROMETER:
                valueMap.put("air_pressure", 90000 + rnd.nextInt(20001));
                break;

            case LOCATION:
                valueMap.put("longitude", -90 + rnd.nextInt(181));
                valueMap.put("latitude", -90 + rnd.nextInt(181));
                break;

            case ACCELEROMETER:
                valueMap.put("x", -10 + rnd.nextInt(21));
                valueMap.put("y", -10 + rnd.nextInt(21));
                valueMap.put("z", -10 + rnd.nextInt(21));
                break;
        }

        return valueMap;
    }
}