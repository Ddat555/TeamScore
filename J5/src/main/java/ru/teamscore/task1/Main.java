package ru.teamscore.task1;

import ru.teamscore.task1.DAO.FlightDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/demo";
        String user = "server";
        String password = "server";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Scanner scanner = new Scanner(System.in)) {

            FlightDAO dao = new FlightDAO(conn);

            System.out.print("Введите код аэропорта: ");
            String airCode = scanner.nextLine();

            System.out.print("Введите дату (YYYY-MM-DD): ");
            String dateStr = scanner.nextLine();

            LocalDate date = LocalDate.parse(dateStr);

            var flightInfoStrings = dao.getFlightArray(airCode, date);

            System.out.printf("%-20s | %-6s | %-8s | %-15s | %-20s | %-15s%n",
                    "Время", "Тип", "Статус", "Код аэропорта", "Город", "Самолет");
            for (var info : flightInfoStrings) {
                System.out.println(info);
            }

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
