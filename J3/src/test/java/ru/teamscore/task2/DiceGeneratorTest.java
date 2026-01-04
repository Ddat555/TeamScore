package ru.teamscore.task2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiceGeneratorTest {

    private final DiceGenerator diceGenerator = new DiceGenerator();

    @Test
    void testGenerate(){
        var arr = diceGenerator.generationArray(5);
        assertEquals(5, arr.length);
        for (var el: arr){
            assertTrue(el >= 1 && el <= 6);
        }
    }
}
