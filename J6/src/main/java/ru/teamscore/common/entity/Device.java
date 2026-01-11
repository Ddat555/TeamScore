package ru.teamscore.common.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "devices", schema = "develop")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32, unique = true)
    private String name;

    @OneToMany(mappedBy = "device")
    private List<Sensor> sensors = new ArrayList<>();


    public Device() {
    }

    public Device(String name) {
        this.name = name;
    }

    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
        sensor.setDevice(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }
}
