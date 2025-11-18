package org.example.task3;

import java.util.Random;
import java.util.Scanner;

public class Task3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Random rnd = new Random();
        int[] numbers = new int[n];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = rnd.nextInt(-100,100);
            System.out.print(numbers[i] + " ");
        }
        int maxCount = Repetitions.countMaxRepeatSign(numbers);
        System.out.println("Result : " + maxCount);
    }
}
