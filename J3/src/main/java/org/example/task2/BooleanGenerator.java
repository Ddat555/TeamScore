package org.example.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BooleanGenerator implements ArrayGenerator<Boolean>{
    @Override
    public List<Boolean> generationArray(int length) {
        List<Boolean> booleanList = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < length; i++) {
            booleanList.add(rnd.nextBoolean());
        }
        return booleanList;
    }
}
