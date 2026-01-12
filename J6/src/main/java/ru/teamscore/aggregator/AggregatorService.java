package ru.teamscore.aggregator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.teamscore.common.SensorType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AggregatorService {

    private final SessionFactory sessionFactory;

    public AggregatorService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private double toDouble(Object value) {
        if (value == null) {
            return 0.0;
        }
        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).doubleValue();
        }
        if (value instanceof Double) {
            return (Double) value;
        }
        if (value instanceof Float) {
            return ((Float) value).doubleValue();
        }
        if (value instanceof Integer) {
            return ((Integer) value).doubleValue();
        }
        if (value instanceof Long) {
            return ((Long) value).doubleValue();
        }
        try {
            return Double.parseDouble(value.toString());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public List<AggregationResult> getAggregateList(SensorType sensorType,
                                                    LocalDateTime startTime,
                                                    LocalDateTime endTime,
                                                    Interval interval,
                                                    String deviceName) {

        try (Session session = sessionFactory.openSession()) {

            List<AggregationResult> results = new ArrayList<>();
            String intervalStr = interval.toString().toLowerCase();

            switch (sensorType) {
                case LIGHT:
                    String lightSql = "SELECT d.name as device_name, " +
                            "DATE_TRUNC('" + intervalStr + "', ld.measure_at) as interval_start, " +
                            "AVG(ld.light) as avg_value " +
                            "FROM develop.light_data ld " +
                            "JOIN develop.sensor s ON ld.sensor_id = s.id " +
                            "JOIN develop.devices d ON s.device_id = d.id " +
                            "WHERE ld.measure_at BETWEEN :startTime AND :endTime " +
                            (deviceName != null ? "AND d.name = :deviceName " : "") +
                            "GROUP BY d.name, DATE_TRUNC('" + intervalStr + "', ld.measure_at) " +
                            "ORDER BY d.name ASC, DATE_TRUNC('" + intervalStr + "', ld.measure_at) DESC";

                    Query lightQuery = session.createNativeQuery(lightSql)
                            .setParameter("startTime", startTime)
                            .setParameter("endTime", endTime);
                    if (deviceName != null) lightQuery.setParameter("deviceName", deviceName);

                    List<Object[]> lightRows = lightQuery.getResultList();
                    for (Object[] row : lightRows) {
                        double avgValue = toDouble(row[2]);
                        results.add(new AggregationResult(
                                (String) row[0],
                                ((Timestamp) row[1]).toLocalDateTime(),
                                String.format("%.2f", avgValue)
                        ));
                    }
                    break;

                case BAROMETER:
                    String barometerSql = "SELECT d.name as device_name, " +
                            "DATE_TRUNC('" + intervalStr + "', bd.measure_at) as interval_start, " +
                            "AVG(bd.air_pressure) as avg_value " +
                            "FROM develop.barometer_data bd " +
                            "JOIN develop.sensor s ON bd.sensor_id = s.id " +
                            "JOIN develop.devices d ON s.device_id = d.id " +
                            "WHERE bd.measure_at BETWEEN :startTime AND :endTime " +
                            (deviceName != null ? "AND d.name = :deviceName " : "") +
                            "GROUP BY d.name, DATE_TRUNC('" + intervalStr + "', bd.measure_at) " +
                            "ORDER BY d.name ASC, DATE_TRUNC('" + intervalStr + "', bd.measure_at) DESC";

                    Query barometerQuery = session.createNativeQuery(barometerSql)
                            .setParameter("startTime", startTime)
                            .setParameter("endTime", endTime);
                    if (deviceName != null) barometerQuery.setParameter("deviceName", deviceName);

                    List<Object[]> barometerRows = barometerQuery.getResultList();
                    for (Object[] row : barometerRows) {
                        double avgValue = toDouble(row[2]);
                        results.add(new AggregationResult(
                                (String) row[0],
                                ((Timestamp) row[1]).toLocalDateTime(),
                                String.format("%.2f", avgValue)
                        ));
                    }
                    break;

                case LOCATION:
                    String locationSql = "SELECT d.name as device_name, " +
                            "DATE_TRUNC('" + intervalStr + "', ld.measure_at) as interval_start, " +
                            "AVG(ld.longitude) as avg_longitude, AVG(ld.latitude) as avg_latitude " +
                            "FROM develop.location_data ld " +
                            "JOIN develop.sensor s ON ld.sensor_id = s.id " +
                            "JOIN develop.devices d ON s.device_id = d.id " +
                            "WHERE ld.measure_at BETWEEN :startTime AND :endTime " +
                            (deviceName != null ? "AND d.name = :deviceName " : "") +
                            "GROUP BY d.name, DATE_TRUNC('" + intervalStr + "', ld.measure_at) " +
                            "ORDER BY d.name ASC, DATE_TRUNC('" + intervalStr + "', ld.measure_at) DESC";

                    Query locationQuery = session.createNativeQuery(locationSql)
                            .setParameter("startTime", startTime)
                            .setParameter("endTime", endTime);
                    if (deviceName != null) locationQuery.setParameter("deviceName", deviceName);

                    List<Object[]> locationRows = locationQuery.getResultList();
                    for (Object[] row : locationRows) {
                        double avgLongitude = toDouble(row[2]);
                        double avgLatitude = toDouble(row[3]);
                        String value = String.format("%.4f;%.4f", avgLongitude, avgLatitude);
                        results.add(new AggregationResult(
                                (String) row[0],
                                ((Timestamp) row[1]).toLocalDateTime(),
                                value
                        ));
                    }
                    break;

                case ACCELEROMETER:
                    String accelerometerSql = "SELECT d.name as device_name, " +
                            "DATE_TRUNC('" + intervalStr + "', ad.measure_at) as interval_start, " +
                            "AVG(ad.x) as avg_x, AVG(ad.y) as avg_y, AVG(ad.z) as avg_z " +
                            "FROM develop.accelerometer_data ad " +
                            "JOIN develop.sensor s ON ad.sensor_id = s.id " +
                            "JOIN develop.devices d ON s.device_id = d.id " +
                            "WHERE ad.measure_at BETWEEN :startTime AND :endTime " +
                            (deviceName != null ? "AND d.name = :deviceName " : "") +
                            "GROUP BY d.name, DATE_TRUNC('" + intervalStr + "', ad.measure_at) " +
                            "ORDER BY d.name ASC, DATE_TRUNC('" + intervalStr + "', ad.measure_at) DESC";

                    Query accelerometerQuery = session.createNativeQuery(accelerometerSql)
                            .setParameter("startTime", startTime)
                            .setParameter("endTime", endTime);
                    if (deviceName != null) accelerometerQuery.setParameter("deviceName", deviceName);

                    List<Object[]> accelerometerRows = accelerometerQuery.getResultList();
                    for (Object[] row : accelerometerRows) {
                        double avgX = toDouble(row[2]);
                        double avgY = toDouble(row[3]);
                        double avgZ = toDouble(row[4]);
                        String value = String.format("%.2f;%.2f;%.2f", avgX, avgY, avgZ);
                        results.add(new AggregationResult(
                                (String) row[0],
                                ((Timestamp) row[1]).toLocalDateTime(),
                                value
                        ));
                    }
                    break;
            }

            if (results.isEmpty()) {
                System.out.println("Нет данных за указанный период");
                return null;
            }
            return results;
        }
    }
}