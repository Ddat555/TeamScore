package ru.teamscore.task2;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayGeneratorTest {


    @Test
    void booleanGeneratorTest() throws JsonProcessingException {
        RandomArrayService randomArrayService = new RandomArrayService(GeneratorType.BOOL);
        assertEquals("[true,true,false,true,true]", randomArrayService.generateAsJson(5));
    }

    @Test
    void diceGeneratorTest() throws JsonProcessingException {
        RandomArrayService randomArrayService = new RandomArrayService(GeneratorType.DICE);
        assertEquals("[2,5,5,1,2]", randomArrayService.generateAsJson(5));
    }

    @Test
    void carNumberGeneratorTest() throws JsonProcessingException {
        RandomArrayService randomArrayService = new RandomArrayService(GeneratorType.CAR_NUMBER);
        assertEquals("[\"K048KY\",\"C833KP\",\"P729CO\",\"C938CX\",\"A182KX\"]", randomArrayService.generateAsJson(5));
    }
}