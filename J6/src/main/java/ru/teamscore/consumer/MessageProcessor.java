package ru.teamscore.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import ru.teamscore.common.SensorType;
import ru.teamscore.common.entity.RawSensorData;
import ru.teamscore.common.entity.sensorsDataEntity.AccelerometerData;
import ru.teamscore.common.entity.sensorsDataEntity.BarometerData;
import ru.teamscore.common.entity.sensorsDataEntity.LightData;
import ru.teamscore.common.entity.sensorsDataEntity.LocationData;

import java.util.Map;

public class MessageProcessor {
    private final ObjectMapper mapper = new ObjectMapper();

    public void processRawData(RawSensorData rawData, Session session) throws JsonProcessingException {
        SensorType type = rawData.getSensor().getType();
        Map<String, Object> res = mapper.readValue(rawData.getValue(), Map.class);

        switch (type) {
            case LIGHT:
                saveLightData(rawData, res, session);
                break;
            case LOCATION:
                saveLocationData(rawData, res, session);
                break;
            case BAROMETER:
                saveBarometerData(rawData, res, session);
                break;
            case ACCELEROMETER:
                saveAccelerometerData(rawData, res, session);
                break;
            default:
                throw new IllegalArgumentException("Unsupported sensor type: " + type);
        }
    }

    private void saveLightData(RawSensorData rawData, Map<String, Object> res, Session session) {
        int lightValue = ((Number) res.get("light")).intValue();
        LightData lightData = new LightData(
                rawData.getSensor(),
                rawData.getMeasuredAt(),
                lightValue);
        session.save(lightData);
    }

    private void saveLocationData(RawSensorData rawData, Map<String, Object> res, Session session) {
        double longitude = ((Number) res.get("longitude")).doubleValue();
        double latitude = ((Number) res.get("latitude")).doubleValue();
        LocationData locationData = new LocationData(
                rawData.getSensor(),
                rawData.getMeasuredAt(),
                longitude,
                latitude
        );
        session.save(locationData);
    }

    private void saveBarometerData(RawSensorData rawData, Map<String, Object> res, Session session) {
        double pressure = ((Number) res.get("air_pressure")).doubleValue();
        BarometerData barometerData = new BarometerData(
                rawData.getSensor(),
                rawData.getMeasuredAt(),
                pressure
        );
        session.save(barometerData);
    }

    private void saveAccelerometerData(RawSensorData rawData, Map<String, Object> res, Session session) {
        double x = ((Number) res.get("x")).doubleValue();
        double y = ((Number) res.get("y")).doubleValue();
        double z = ((Number) res.get("z")).doubleValue();
        AccelerometerData accelerometerData = new AccelerometerData(
                rawData.getSensor(),
                rawData.getMeasuredAt(),
                x, y, z
        );
        session.save(accelerometerData);
    }
}
