package ru.teamscore.task2;

import java.util.Random;

public class CarNumberGenerator implements ArrayGenerator<String> {

    private final Random rnd = new Random(100);
    private final String[] charCarNumber = new String[]{"A", "B", "C", "Y", "X", "P", "O", "K", "M"};

    @Override
    public String[] generationArray(int length) {
        String[] arr = new String[length];
        for (int i = 0; i < length; i++) {
            arr[i] = generateNumber();
        }
        return arr;
    }

    private String generateNumber() {
        StringBuilder builder = new StringBuilder();

        builder.append(charCarNumber[rnd.nextInt(charCarNumber.length)]);
        builder.append(rnd.nextInt(10));
        builder.append(rnd.nextInt(10));
        builder.append(rnd.nextInt(10));
        builder.append(charCarNumber[rnd.nextInt(charCarNumber.length)]);
        builder.append(charCarNumber[rnd.nextInt(charCarNumber.length)]);

        return builder.toString();
    }
}
