package org.example.task3;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputOutput {

    private final ISOCalculator calculator = new ISOCalculator();


<<<<<<< Updated upstream
    private boolean validationInputData(int year, int week){
=======
    public boolean validationInputData(int year, int week) {
>>>>>>> Stashed changes
        if (year < 1 || year > 9999) {
            System.err.println("Год должен быть больше 0 и меньше 10000");
            return false;
        }
        int maxNumberOfWeeks = LocalDate.of(year, 12, 28)
                .get(WeekFields.ISO.weekOfWeekBasedYear());

        if (week < 1 || week > maxNumberOfWeeks) {
            System.err.println("Неделя должна быть больше 0 и меньше " + maxNumberOfWeeks);
            return false;
        }
        return true;
    }

    public String formatDateRange(LocalDate monday, LocalDate sunday) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return formatter.format(monday) + " – " + formatter.format(sunday);
    }

    public void process(InputStream in) {
        try {
            Scanner scanner = new Scanner(in);
            System.out.print("Введите год (1-9999): ");
            int year = scanner.nextInt();

            System.out.print("Введите номер недели ISO (1-52 или 53): ");
            int weekNumber = scanner.nextInt();

            if(!validationInputData(year,weekNumber)){
                return;
            }

            LocalDate[] dates = calculator.calculateWeekDates(year, weekNumber);

            String result = formatDateRange(dates[0], dates[1]);
            System.out.printf("\nДиапазон дат %S недели %S года: %n", weekNumber, year);
            System.out.println(result);

        } catch (InputMismatchException e) {
            System.err.println("Ошибка: Введено некорректное число.");
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Неожиданная ошибка: " + e.getMessage());
        }
    }
}
