package org.example.task6;

import java.util.*;

public class ArrayStatistics {

    private final int[] numbers;

    public ArrayStatistics(int[] numbers) {
        this.numbers = numbers;
    }

    public int[] getMode() {
        if (numbers.length == 0)
            return new int[0];
        Map<Integer, Integer> numbersMap = new HashMap<>();
        for (int n : numbers) {
            numbersMap.put(n, numbersMap.getOrDefault(n, 0) + 1);
        }
        int maxCount = 0;
        for (Integer n : numbersMap.values()) {
            if (n > maxCount)
                maxCount = n;
        }
        List<Integer> modes = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : numbersMap.entrySet()) {
            if (entry.getValue() == maxCount) {
                modes.add(entry.getKey());
            }
        }
        return modes.stream().mapToInt(i -> i).toArray();
    }

    public double getMedian() {
        if (numbers.length == 0)
            return 0;
        int[] sorted = numbers.clone();
        Arrays.sort(sorted);
        if (sorted.length % 2 == 1) {
            return sorted[sorted.length / 2];
        } else {
            int a = sorted[sorted.length / 2 - 1];
            int b = sorted[sorted.length / 2];
            return (a + b) / 2d;
        }
    }

    public double getAverage() {
        if (numbers.length == 0)
            return 0;
        int sum = 0;
        for (int n : numbers) {
            sum += n;
        }
        return (double) sum / numbers.length;
    }

    public double getVariance() {
        if (numbers.length == 0)
            return 0;
        var avg = getAverage();
        double sum = 0;
        for (int n : numbers) {
            double q = Math.pow((n - avg), 2);
            sum += q;
        }
        return sum / numbers.length;
    }

    public double getGeometricMean() {
        if (numbers.length == 0)
            return 0;
        double mul = 1;
        for (int n : numbers) {
            mul *= n;
        }

        return Math.pow(mul, 1.0 / numbers.length);
    }

    public int[] shuffle() {
        if (numbers.length == 0)
            return numbers;
        int[] shuffled = numbers.clone();
        Random rnd = new Random();

        for (int i = 0; i < numbers.length; i++) {
            int newIndex = rnd.nextInt(numbers.length);
            int temp = shuffled[i];
            shuffled[i] = shuffled[newIndex];
            shuffled[newIndex] = temp;
        }

        return shuffled;
    }

    public int[] sample(int newLength) {
        if (numbers.length == 0)
            return numbers;

        int[] sampled = new int[newLength];
        Random rnd = new Random();

        for (int i = 0; i < newLength; i++) {
            int rndIndex = rnd.nextInt(numbers.length);
            sampled[i] = numbers[rndIndex];
        }

        return sampled;
    }

}
