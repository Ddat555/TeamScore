package ru.teamscore.aggregator;

import java.time.LocalDateTime;

public class AggregationResult {
    private String deviceName;
    private LocalDateTime dateTime;
    private String value;

    public AggregationResult(String deviceName, LocalDateTime dateTime, String value) {
        this.deviceName = deviceName;
        this.dateTime = dateTime;
        this.value = value;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
