package org.example.task2;

import java.math.BigInteger;

public class MathDegree {

    public static int countNumberAfterDegree(long num, int exp){
        BigInteger bigInteger = BigInteger.valueOf(num).pow(exp);
        return bigInteger.toString().length();
    }
}
