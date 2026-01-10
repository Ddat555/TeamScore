package ru.teamscore.common.entity.sensorsDataEntity;

import ru.teamscore.common.entity.Sensor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "barometer_data", schema = "develop")
public class BarometerData extends SensorData{
    private double air_pressure;

    public BarometerData(Sensor sensor, LocalDateTime measureAt) {
        super(sensor, measureAt);
    }

    public BarometerData(Sensor sensor, LocalDateTime measureAt, float air_pressure) {
        super(sensor, measureAt);
        this.air_pressure = air_pressure;
    }
}
