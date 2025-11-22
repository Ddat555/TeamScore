package org.example.task6;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class Task6 {

    public static void main(String[] args) {
        ArrayStatistics statistics1 = new ArrayStatistics(new int[0]);
        ArrayStatistics statistics2 = new ArrayStatistics(new int[]{3});
        ArrayStatistics statistics3 = new ArrayStatistics(new int[]{2,2,3});
        ArrayStatistics statistics4 = new ArrayStatistics(new Random(100).ints(100_000).toArray());
        printStatistic(statistics1);
        printStatistic(statistics2);
        printStatistic(statistics3);
//        printStatistic(statistics4);
    }


    private static void printStatistic(ArrayStatistics arrayStatistics){
        System.out.println("==========================================");
        System.out.println("Mode :" + Arrays.toString(arrayStatistics.getMode()));
        System.out.println("Median : " + arrayStatistics.getMedian());
        System.out.println("Variance : " + arrayStatistics.getVariance());
        System.out.println("Geometric mean : " + arrayStatistics.getGeometricMean());
        System.out.println("Shuffle : " + Arrays.toString(arrayStatistics.shuffle()));
        System.out.println("Sample : " + Arrays.toString(arrayStatistics.sample(5)));
    }
}
