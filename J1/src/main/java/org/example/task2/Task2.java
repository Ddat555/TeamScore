package org.example.task2;

import java.math.BigInteger;
import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите a ( 0 < a <= 2^32): ");
        long a = scanner.nextLong();
        System.out.print("Введите n ( 0 <= n <= 1000): ");
        int n = scanner.nextInt();
        BigInteger num = BigInteger.valueOf(a).pow(n);
        System.out.println("Number : " + num);
        System.out.println("Result: " + num.toString().length());
    }
}
