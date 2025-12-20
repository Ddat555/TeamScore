package ru.teamscore.task2;

import java.util.Random;

public class BooleanGenerator implements ArrayGenerator<Boolean> {

    private final Random rnd;

    public BooleanGenerator() {
        this.rnd = new Random();
    }

    public BooleanGenerator(Random rnd) {
        this.rnd = rnd;
    }

    @Override
    public Boolean[] generationArray(int length) {
        Boolean[] arr = new Boolean[length];

        for (int i = 0; i < length; i++) {
            arr[i] = rnd.nextBoolean();
        }
        return arr;
    }
}
