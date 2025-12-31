package ru.teamscore.task1.DTO;

import java.time.LocalDateTime;

public class FlightInfoDTO {
    private int flightId;
    private String status;
    private String type;
    private LocalDateTime scheduledTime;
    private String airportCode;
    private String city;
    private String aircraftModel;

    public FlightInfoDTO(int flightId, String status, String type, LocalDateTime scheduledTime, String airportCode, String city, String aircraftModel) {
        this.flightId = flightId;
        this.status = status;
        this.type = type;
        this.scheduledTime = scheduledTime;
        this.airportCode = airportCode;
        this.city = city;
        this.aircraftModel = aircraftModel;
    }

    public int getFlightId() {
        return flightId;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public String getCity() {
        return city;
    }

    public String getAircraftModel() {
        return aircraftModel;
    }
}
