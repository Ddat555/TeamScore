package ru.teamscore.task2;

import ru.teamscore.task2.DAO.PriceUpdateDAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/demo";
        String user = "server";
        String password = "server";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Scanner scanner = new Scanner(System.in)) {

            PriceUpdateDAO dao = new PriceUpdateDAO(connection);

            System.out.print("Введите номер рейса: ");
            String flightNo = scanner.nextLine();

            System.out.print("Введите класс обслуживания: ");
            String fareCond = scanner.nextLine();

            System.out.print("Введите дату (YYYY-MM-DD: ");
            LocalDate bookDate = LocalDate.parse(scanner.nextLine());

            System.out.print("Введите новую стоимость: ");
            BigDecimal price = BigDecimal.valueOf(scanner.nextDouble());

            dao.updatePrice(flightNo, fareCond, bookDate, price);

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
