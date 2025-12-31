package ru.teamscore.task1.DAO;

import ru.teamscore.task1.DTO.FlightInfoDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO {

    private Connection connection;

    public FlightDAO(Connection connection) {
        this.connection = connection;
    }

    public String[] getFlightArray(String airportCode, LocalDate date) throws SQLException {
        String sql = """
                SELECT\s
                                f.flight_id,
                                f.status,
                                CASE\s
                                    WHEN r.departure_airport = ? THEN 'Вылет'\s
                                    ELSE 'Прилет'\s
                                END as type,
                                CASE\s
                                    WHEN r.departure_airport = ? THEN f.scheduled_departure
                                    ELSE f.scheduled_arrival
                                END as scheduled_time,
                                CASE\s
                                    WHEN r.departure_airport = ? THEN arr.airport_code
                                    ELSE dep.airport_code
                                END as airport_code,
                                CASE\s
                                    WHEN r.departure_airport = ? THEN arr.city
                                    ELSE dep.city
                                END as city,
                                a.model as aircraft_model
                            FROM bookings.flights f
                            JOIN bookings.routes r ON f.route_no = r.route_no
                            JOIN bookings.airports dep ON r.departure_airport = dep.airport_code
                            JOIN bookings.airports arr ON r.arrival_airport = arr.airport_code
                            JOIN bookings.airplanes a ON r.airplane_code = a.airplane_code
                            WHERE (r.departure_airport = ? OR r.arrival_airport = ?)
                              AND (DATE(f.scheduled_departure) = ?\s
                                   OR DATE(f.scheduled_arrival) = ?)
                            ORDER BY scheduled_time
                """;

        List<FlightInfoDTO> flights = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, airportCode);
            statement.setString(2, airportCode);
            statement.setString(3, airportCode);
            statement.setString(4, airportCode);
            statement.setString(5, airportCode);
            statement.setString(6, airportCode);
            statement.setDate(7, Date.valueOf(date));
            statement.setDate(8, Date.valueOf(date));

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                FlightInfoDTO flightInfoDTO = new FlightInfoDTO(
                        rs.getInt("flight_id"),
                        rs.getString("status"),
                        rs.getString("type"),
                        rs.getTimestamp("scheduled_time").toLocalDateTime(),
                        rs.getString("airport_code"),
                        rs.getString("city"),
                        rs.getString("aircraft_model")
                );
                flights.add(flightInfoDTO);
            }
        }
        return convertFromListToArrayString(flights);
    }

    private String[] convertFromListToArrayString(List<FlightInfoDTO> flightInfoDTOList){
        String[] result = new String[flightInfoDTOList.size()];
        for (int i = 0; i < flightInfoDTOList.size(); i++) {
            FlightInfoDTO flight = flightInfoDTOList.get(i);
            result[i] = String.format("%-20s | %-6s | %-8s | %-15s | %-20s | %-15s",
                    flight.getScheduledTime(),
                    flight.getType(),
                    flight.getStatus(),
                    flight.getAirportCode(),
                    flight.getCity(),
                    flight.getAircraftModel());
        }
        return result;
    }
}
