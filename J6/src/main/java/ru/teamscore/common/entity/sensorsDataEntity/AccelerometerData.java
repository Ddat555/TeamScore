package ru.teamscore.common.entity.sensorsDataEntity;

import ru.teamscore.common.entity.Sensor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "accelerometer_data", schema = "develop")
public class AccelerometerData extends SensorData{
    private double x;
    private double y;
    private double z;

    public AccelerometerData(Sensor sensor, LocalDateTime measureAt) {
        super(sensor, measureAt);
    }

    public AccelerometerData(Sensor sensor, LocalDateTime measureAt, double x, double y, double z) {
        super(sensor, measureAt);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
