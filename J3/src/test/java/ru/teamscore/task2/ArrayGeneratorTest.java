package ru.teamscore.task2;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class ArrayGeneratorTest {


    @Test
    void booleanGeneratorTest() throws JsonProcessingException {
        BooleanGenerator booleanGenerator = new BooleanGenerator();
        var arr = booleanGenerator.generationArray(5);
        assertEquals(5, arr.length);
        assertTrue(Arrays.asList(arr).contains(Boolean.TRUE) ||
                Arrays.asList(arr).contains(Boolean.FALSE));
    }

    @Test
    void diceGeneratorTest() throws JsonProcessingException {
        DiceGenerator diceGenerator = new DiceGenerator();
        var arr = diceGenerator.generationArray(5);
        assertEquals(5, arr.length);
        for (var el: arr){
            assertTrue(el >= 1 && el <= 6);
        }
    }

    @Test
    void carNumberGeneratorTest() throws JsonProcessingException {
        CarNumberGenerator carNumberGenerator = new CarNumberGenerator();
        var arr = carNumberGenerator.generationArray(5);
        assertEquals(5, arr.length);
        Pattern pattern = Pattern.compile("^[ABCYXPKOM]\\d{3}[ABCYXPKOM]{2}$");
        for (String number : arr) {
            assertTrue(pattern.matcher(number).matches(),
                    "Неверный формат: " + number);
        }
    }
}