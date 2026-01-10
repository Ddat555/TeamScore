package ru.teamscore.common.entity;

import ru.teamscore.common.SensorType;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "sensor", schema = "develop")
public class Sensor {

    @Id
    @Column(length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SensorType type;

    public Sensor() {
    }

    public Sensor(String id, SensorType type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public SensorType getType() {
        return type;
    }

    public void setType(SensorType type) {
        this.type = type;
    }
}
