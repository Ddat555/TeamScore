package org.example.task1;


public class Task1 {

    public static void main(String[] args) {
        double[] numbers = {30.0d, 10000.1d, 12.5d, 99.99d, 0.0d, -23.45d, -4.5d, -129.675d};


        System.out.printf("%-12s | %-12s | %-12s | %-12s | %-12s%n", "Number", "round()", "floor()", "ceil()", "rint()");
        for (double number : numbers) {
            System.out.printf("%-12.2f | %-12d | %-12d | %-12d | %-12d%n",
                    number,
                    MathRounding.mathRound(number, MathRounding.RoundType.ROUND),
                    MathRounding.mathRound(number, MathRounding.RoundType.FLOOR),
                    MathRounding.mathRound(number, MathRounding.RoundType.CEIL),
                    MathRounding.mathRound(number, MathRounding.RoundType.RINT)
            );
        }
    }
}
