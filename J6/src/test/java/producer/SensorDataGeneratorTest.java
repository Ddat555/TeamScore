package producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import ru.teamscore.common.SensorType;
import ru.teamscore.producer.DeviceManager;
import ru.teamscore.producer.SensorDataGenerator;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SensorDataGeneratorTest {

    private SensorDataGenerator sensorDataGenerator = new SensorDataGenerator(
            null
    );

    @Test
    void generateSensorValueLight() throws JsonProcessingException {
        Map<String, Object> result = (Map<String, Object>) sensorDataGenerator.generateSensorValue(SensorType.LIGHT);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(result);
        Pattern lightPattern = Pattern.compile("\\{\"light\":(\\d{1,4})}");
        Matcher matcher = lightPattern.matcher(json);
        assertTrue(matcher.find(), json);
        int value = Integer.parseInt(matcher.group(1));
        assertTrue(value >= 0 && value <= 1023, json);
    }

    @Test
    void generateSensorValueAccelerometer() throws JsonProcessingException {
        Map<String, Object> result = (Map<String, Object>) sensorDataGenerator.generateSensorValue(SensorType.ACCELEROMETER);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(result);
        Pattern accelerometerPattern = Pattern.compile(
                "\\{\"x\":(-?\\d+(?:\\.\\d+)?),\"y\":(-?\\d+(?:\\.\\d+)?),\"z\":(-?\\d+(?:\\.\\d+)?)}"
        );
        Matcher matcher = accelerometerPattern.matcher(json);
        assertTrue(matcher.find(), "JSON не соответствует паттерну: " + json);

        double x = Double.parseDouble(matcher.group(1));
        double y = Double.parseDouble(matcher.group(2));
        double z = Double.parseDouble(matcher.group(3));

        assertTrue(x >= -25 && x <= 25, "x=" + x + ", JSON: " + json);
        assertTrue(y >= -25 && y <= 25, "y=" + y + ", JSON: " + json);
        assertTrue(z >= -25 && z <= 25, "z=" + z + ", JSON: " + json);
    }

    @Test
    void generateSensorValueBarometer() throws JsonProcessingException {
        Map<String, Object> result = (Map<String, Object>) sensorDataGenerator.generateSensorValue(SensorType.BAROMETER);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(result);
        Pattern barometerPattern = Pattern.compile(
                "\\{\"air_pressure\":(\\d+(?:\\.\\d+)?)}"
        );
        Matcher matcher = barometerPattern.matcher(json);
        assertTrue(matcher.find(), json);
        double value = Double.parseDouble(matcher.group(1));
        assertTrue(value >= 90000 && value <= 110000, json);
    }

    @Test
    void generateSensorValueLocation() throws JsonProcessingException {
        Map<String, Object> result = (Map<String, Object>) sensorDataGenerator.generateSensorValue(SensorType.LOCATION);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(result);
        Pattern locationPattern = Pattern.compile(
                "\\{\"latitude\":(-?\\d+(?:\\.\\d+)?),\"longitude\":(-?\\d+(?:\\.\\d+)?)}"
        );
        Matcher matcher = locationPattern.matcher(json);
        assertTrue(matcher.find(), json);
        double longitude = Double.parseDouble(matcher.group(1));
        double latitude = Double.parseDouble(matcher.group(2));
        assertTrue(longitude >= -180 && longitude <= 180, json);
        assertTrue(latitude >= -90 && latitude <= 90, json);
    }
}