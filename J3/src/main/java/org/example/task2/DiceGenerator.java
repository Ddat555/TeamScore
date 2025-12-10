package org.example.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiceGenerator implements ArrayGenerator<Integer>{
    @Override
    public List<Integer> generationArray(int length) {
        List<Integer> integerList = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < length; i++) {
            integerList.add(rnd.nextInt(1,7));
        }
        return integerList;
    }
}
