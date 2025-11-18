package org.example.task1;

public class MathRounding {

    public enum RoundType{
        ROUND,
        FLOOR,
        CEIL,
        RINT
    }

    public static long mathRound(double num, RoundType roundType){
        switch (roundType){
            case ROUND:
                return Math.round(num);
            case FLOOR:
                return (long)Math.floor(num);
            case CEIL:
                return (long)Math.ceil(num);
            case RINT:
                return (long)Math.rint(num);
            default:
                throw new RuntimeException("Unexpected RoundType: " + roundType);
        }
    }
}
