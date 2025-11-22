package org.example.task2;

import java.math.BigInteger;

public class MathDegree {

    public static int countNumberAfterDegree(long num, int exp){
        if (num <= 0 || exp < 0) {
            throw new IllegalArgumentException("Основание должно быть > 0, степень >= 0");
        }
        BigInteger bigInteger = BigInteger.valueOf(num).pow(exp);
        return bigInteger.toString().length();
    }
}
