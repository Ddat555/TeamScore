package org.example.task3;

public class Repetitions {

    public static int countMaxRepeatSign(int[] numbers){
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
                digit = sign;
            }
        }

        if (count > maxCount) {
            maxCount = count;
        }
        return maxCount;
    }

    private static int getSign(int num){
        return Integer.compare(num, 0);
    }
}
