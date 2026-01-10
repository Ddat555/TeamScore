package ru.teamscore.common.entity.sensorsDataEntity;

import ru.teamscore.common.entity.Sensor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "light_data", schema = "develop")
public class LightData extends SensorData{

    private int light;

    public LightData(Sensor sensor, LocalDateTime measureAt) {
        super(sensor, measureAt);
    }

    public LightData(Sensor sensor, LocalDateTime measureAt, int light) {
        super(sensor, measureAt);
        this.light = light;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }
}
