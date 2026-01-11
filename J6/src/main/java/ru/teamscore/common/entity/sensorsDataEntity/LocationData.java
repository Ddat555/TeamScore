package ru.teamscore.common.entity.sensorsDataEntity;

import ru.teamscore.common.entity.Sensor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "location_data", schema = "develop")
public class LocationData extends SensorData {

    private double longitude;
    private double latitude;

    public LocationData(Sensor sensor, LocalDateTime measureAt) {
        super(sensor, measureAt);
    }

    public LocationData(Sensor sensor, LocalDateTime measureAt, double longitude, double latitude) {
        super(sensor, measureAt);
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
