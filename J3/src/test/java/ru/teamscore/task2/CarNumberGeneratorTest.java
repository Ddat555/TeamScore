package ru.teamscore.task2;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarNumberGeneratorTest {

    private final CarNumberGenerator carNumberGenerator = new CarNumberGenerator();

    @Test
    void testGenerate() {
        var arr = carNumberGenerator.generationArray(5);
        assertEquals(5, arr.length);
        Pattern pattern = Pattern.compile("^[ABCYXPKOM]\\d{3}[ABCYXPKOM]{2}$");
        for (String number : arr) {
            assertTrue(pattern.matcher(number).matches());
        }

    }
}
