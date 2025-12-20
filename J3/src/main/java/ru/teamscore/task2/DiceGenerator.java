package ru.teamscore.task2;

import java.util.Random;

public class DiceGenerator implements ArrayGenerator<Integer> {

    private final Random rnd;

    public DiceGenerator() {
        this.rnd = new Random();
    }

    public DiceGenerator(Random rnd) {
        this.rnd = rnd;
    }

    @Override
    public Integer[] generationArray(int length) {
        Integer[] arr = new Integer[length];
        for (int i = 0; i < length; i++) {
            arr[i] = rnd.nextInt(1, 7);
        }
        return arr;
    }
}
