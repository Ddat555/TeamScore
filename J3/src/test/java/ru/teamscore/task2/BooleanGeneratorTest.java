package ru.teamscore.task2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BooleanGeneratorTest {

    private final BooleanGenerator booleanGenerator = new BooleanGenerator();

    @Test
    void testGenerate(){
        var arr = booleanGenerator.generationArray(5);
        assertEquals(5, arr.length);
        assertTrue(Arrays.asList(arr).contains(Boolean.TRUE) ||
                Arrays.asList(arr).contains(Boolean.FALSE));
    }
}
