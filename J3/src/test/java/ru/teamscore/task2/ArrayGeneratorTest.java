package ru.teamscore.task2;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayGeneratorTest {


    @Test
    void booleanGeneratorTest() throws JsonProcessingException {
        BooleanGenerator booleanGenerator = new BooleanGenerator(new Random(100));
        assertEquals("[true, true, false, true, true]", Arrays.toString(booleanGenerator.generationArray(5)));
    }

    @Test
    void diceGeneratorTest() throws JsonProcessingException {
        DiceGenerator diceGenerator = new DiceGenerator(new Random(100));
        assertEquals("[2, 5, 5, 1, 2]", Arrays.toString(diceGenerator.generationArray(5)));
    }

    @Test
    void carNumberGeneratorTest() throws JsonProcessingException {
        CarNumberGenerator carNumberGenerator = new CarNumberGenerator(new Random(100));
        assertEquals("[K048KY, C833KP, P729CO, C938CX, A182KX]", Arrays.toString(carNumberGenerator.generationArray(5)));
    }
}