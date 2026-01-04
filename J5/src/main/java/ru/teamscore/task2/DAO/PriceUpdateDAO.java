package ru.teamscore.task2.DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class PriceUpdateDAO {

    private Connection connection;

    public PriceUpdateDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean updatePrice(String routeNo, String fareConditions,
                               LocalDate bookDate, BigDecimal newPrice) throws SQLException {

        boolean oldAutoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);

        try {
            String updatePriceSQL = """
                    UPDATE segments s
                    SET price = ?
                    FROM flights f, tickets t, bookings b
                    WHERE s.flight_id = f.flight_id
                      AND s.ticket_no = t.ticket_no
                      AND t.book_ref = b.book_ref
                      AND s.fare_conditions = ?
                      AND f.route_no = ?
                      AND DATE(b.book_date) = ?
                    """;

            try (PreparedStatement priceStmt = connection.prepareStatement(updatePriceSQL)) {
                priceStmt.setBigDecimal(1, newPrice);
                priceStmt.setString(2, fareConditions);
                priceStmt.setString(3, routeNo);
                priceStmt.setDate(4, Date.valueOf(bookDate));

                int updated = priceStmt.executeUpdate();

                if (updated == 0) {
                    connection.rollback();
                    return false;
                }
            }

            String updateTotalSQL = """
                    UPDATE bookings
                    SET total_amount = (
                        SELECT COALESCE(SUM(s.price), 0)
                        FROM segments s
                        JOIN tickets t ON s.ticket_no = t.ticket_no
                        WHERE t.book_ref = bookings.book_ref
                    )
                    WHERE DATE(book_date) = ?
                    """;

            try (PreparedStatement totalStmt = connection.prepareStatement(updateTotalSQL)) {
                totalStmt.setDate(1, Date.valueOf(bookDate));
                totalStmt.executeUpdate();
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(oldAutoCommit);
        }
    }
}