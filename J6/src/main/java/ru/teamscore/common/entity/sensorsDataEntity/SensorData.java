package ru.teamscore.common.entity.sensorsDataEntity;

import ru.teamscore.common.entity.Sensor;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sensor_id", nullable = false)
    private Sensor sensor;

    @Column(name = "measure_at", nullable = false)
    private LocalDateTime measureAt;

    public SensorData(Sensor sensor, LocalDateTime measureAt) {
        this.sensor = sensor;
        this.measureAt = measureAt;
    }
}
