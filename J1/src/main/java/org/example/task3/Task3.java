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

        //------------------------------
        int digit = 0;
        int count = 0;
        int maxCount = 0;
        for (int num : numbers){
            int sign = getSign(num);
            if(sign == 0){
                if(count > maxCount){
                    maxCount = count;
                }
                count = 1;
                digit = 0;
            }
            else if(digit == 0 || sign == digit){
                count++;
                digit = sign;
            }
            else {
                if(count > maxCount){
                    maxCount = count;
                }
                count = 1;
                digit = 0;
            }
        }

        if (count > maxCount) {
            maxCount = count;
        }
        System.out.println("Result : " + maxCount);
    }

    private static int getSign(int num){
        if(num > 0) return 1;
        if(num < 0) return -1;
        return 0;
    }
}
