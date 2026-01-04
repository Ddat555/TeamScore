package ru.teamscore.task1.DAO;


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
        List<String> results = new ArrayList<>();

        String sql = """
                SELECT 
                    f.flight_id,
                    f.status,
                    f.scheduled_departure,
                    f.scheduled_arrival,
                    r.departure_airport,
                    r.arrival_airport,
                    dep.city as dep_city,
                    arr.city as arr_city,
                    ap.model
                FROM flights f
                JOIN routes r ON f.route_no = r.route_no
                JOIN airports dep ON r.departure_airport = dep.airport_code
                JOIN airports arr ON r.arrival_airport = arr.airport_code
                JOIN airplanes ap ON r.airplane_code = ap.airplane_code
                WHERE (r.departure_airport = ? OR r.arrival_airport = ?)
                  AND (DATE(f.scheduled_departure) = ? OR DATE(f.scheduled_arrival) = ?)
                ORDER BY f.scheduled_departure
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, airportCode);
            stmt.setString(2, airportCode);
            stmt.setDate(3, Date.valueOf(date));
            stmt.setDate(4, Date.valueOf(date));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String departureAirport = rs.getString("departure_airport");
                String type = departureAirport.equals(airportCode) ? "Вылет" : "Прилет";

                Timestamp scheduledTime = departureAirport.equals(airportCode)
                        ? rs.getTimestamp("scheduled_departure")
                        : rs.getTimestamp("scheduled_arrival");

                String airportCodeResult = departureAirport.equals(airportCode)
                        ? rs.getString("arrival_airport")
                        : rs.getString("departure_airport");

                String city = departureAirport.equals(airportCode)
                        ? rs.getString("arr_city")
                        : rs.getString("dep_city");

                String flightInfo = String.format("%-20s | %-6s | %-8s | %-15s | %-20s | %-15s",
                        scheduledTime.toLocalDateTime(),
                        type,
                        rs.getString("status"),
                        airportCodeResult,
                        city,
                        rs.getString("model"));

                results.add(flightInfo);
            }
        }

        return results.toArray(new String[0]);
    }
}
