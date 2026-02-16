package ru.teamscore.task3;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            MOEXSearcher moexSearcher = new MOEXSearcher();
            while (true) {
                System.out.print("Запрос: ");
                String input = scanner.nextLine();
                if ("/exit".equals(input))
                    break;
                else if (!input.isEmpty()) {
                    moexSearcher.searchAndSave(input);
                }
            }
            moexSearcher.waitForAllTasks();
        } catch (IOException e) {
            System.out.println("ОШИБКА!");
            throw new RuntimeException(e);
        }
    }
}
