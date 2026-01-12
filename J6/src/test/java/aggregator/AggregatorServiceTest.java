package aggregator;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.teamscore.aggregator.AggregationResult;
import ru.teamscore.aggregator.AggregatorService;
import ru.teamscore.aggregator.Interval;
import ru.teamscore.common.SensorType;
import ru.teamscore.common.entity.Device;
import ru.teamscore.common.entity.Sensor;
import ru.teamscore.common.entity.sensorsDataEntity.AccelerometerData;
import ru.teamscore.common.entity.sensorsDataEntity.BarometerData;
import ru.teamscore.common.entity.sensorsDataEntity.LightData;
import ru.teamscore.common.entity.sensorsDataEntity.LocationData;
import ru.teamscore.common.utils.TestHibernateUtil;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AggregatorServiceTest {


    @BeforeAll
    static void setUpTestData() {
        try (Session session = TestHibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            session.createNativeQuery(
                    "CREATE SCHEMA IF NOT EXISTS develop"
            ).executeUpdate();

            Device device1 = new Device("dev1");
            Device device2 = new Device("dev2");
            Device device3 = new Device("dev3");
            session.save(device1);
            session.save(device2);
            session.save(device3);

            Sensor lightSensor1 = new Sensor("light1", SensorType.LIGHT);
            lightSensor1.setDevice(device1);
            device1.addSensor(lightSensor1);

            Sensor barometerSensor1 = new Sensor("bar1", SensorType.BAROMETER);
            barometerSensor1.setDevice(device1);
            device1.addSensor(barometerSensor1);

            Sensor locationSensor1 = new Sensor("loc1", SensorType.LOCATION);
            locationSensor1.setDevice(device1);
            device1.addSensor(locationSensor1);

            Sensor accelerometerSensor1 = new Sensor("acc1", SensorType.ACCELEROMETER);
            accelerometerSensor1.setDevice(device1);
            device1.addSensor(accelerometerSensor1);

            Sensor lightSensor2 = new Sensor("light2", SensorType.LIGHT);
            lightSensor2.setDevice(device2);
            device2.addSensor(lightSensor2);

            Sensor accelerometerSensor2 = new Sensor("acc2", SensorType.ACCELEROMETER);
            accelerometerSensor2.setDevice(device3);
            device3.addSensor(accelerometerSensor2);

            session.save(lightSensor1);
            session.save(barometerSensor1);
            session.save(locationSensor1);
            session.save(accelerometerSensor1);
            session.save(lightSensor2);
            session.save(accelerometerSensor2);

            LightData ld1 = new LightData(lightSensor1, LocalDateTime.of(2026, 1, 1, 10, 0), 500);
            LightData ld2 = new LightData(lightSensor1, LocalDateTime.of(2026, 1, 1, 10, 30), 700);
            LightData ld3 = new LightData(lightSensor1, LocalDateTime.of(2026, 1, 1, 10, 45), 900);
            session.save(ld1);
            session.save(ld2);
            session.save(ld3);

            LightData ld4 = new LightData(lightSensor1, LocalDateTime.of(2026, 1, 2, 10, 0), 1000);
            session.save(ld4);

            BarometerData bd1 = new BarometerData(barometerSensor1, LocalDateTime.of(2026, 1, 1, 10, 15), 101325.0);
            BarometerData bd2 = new BarometerData(barometerSensor1, LocalDateTime.of(2026, 1, 1, 10, 45), 101330.0);
            session.save(bd1);
            session.save(bd2);

            LocationData loc1 = new LocationData(locationSensor1, LocalDateTime.of(2026, 1, 1, 10, 0), 55.7558, 37.6173);
            LocationData loc2 = new LocationData(locationSensor1, LocalDateTime.of(2026, 1, 1, 10, 30), 55.7560, 37.6175);
            session.save(loc1);
            session.save(loc2);

            AccelerometerData acc1 = new AccelerometerData(accelerometerSensor1, LocalDateTime.of(2026, 1, 1, 10, 0), 1.0, 2.0, 3.0);
            AccelerometerData acc2 = new AccelerometerData(accelerometerSensor1, LocalDateTime.of(2026, 1, 1, 10, 30), 2.0, 3.0, 4.0);
            session.save(acc1);
            session.save(acc2);

            LightData ld5 = new LightData(lightSensor2, LocalDateTime.of(2026, 1, 1, 10, 0), 300);
            LightData ld6 = new LightData(lightSensor2, LocalDateTime.of(2026, 1, 1, 10, 30), 600);
            session.save(ld5);
            session.save(ld6);

            AccelerometerData acc3 = new AccelerometerData(accelerometerSensor2, LocalDateTime.of(2026, 1, 1, 10, 0), 0.5, 1.5, 2.5);
            AccelerometerData acc4 = new AccelerometerData(accelerometerSensor2, LocalDateTime.of(2026, 1, 1, 10, 30), 1.5, 2.5, 3.5);
            session.save(acc3);
            session.save(acc4);

            // не должны попасть в результат!!!
            BarometerData ld7 = new BarometerData(barometerSensor1, LocalDateTime.of(2026, 1, 1, 9, 59), 100);
            BarometerData ld8 = new BarometerData(barometerSensor1, LocalDateTime.of(2026, 1, 1, 11, 1), 1100);
            session.save(ld7);
            session.save(ld8);

            tx.commit();
            System.out.println("Тестовые данные для AggregatorService созданы");
        }
    }

    @Test
    void testBarometerAggregationByHour() {
        AggregatorService aggregatorService = new AggregatorService(TestHibernateUtil.getSessionFactory());
        LocalDateTime startTime = LocalDateTime.of(2026, 1, 1, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2026, 1, 1, 11, 0);

        List<AggregationResult> results = aggregatorService.getAggregateList(
                SensorType.BAROMETER, startTime, endTime, Interval.HOUR, null);

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(1, results.size());

        AggregationResult device1Result = results.get(0);
        assertNotNull(device1Result);
        assertEquals("dev1", device1Result.getDeviceName());

        String value = device1Result.getValue();

        assertEquals("101327,50", value);

        assertEquals(LocalDateTime.of(2026, 1, 1, 10, 0), device1Result.getDateTime());
    }

}
