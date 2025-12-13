package ru.teamscore;


import com.fasterxml.jackson.core.JsonProcessingException;
import ru.teamscore.task2.GeneratorType;
import ru.teamscore.task2.RandomArrayService;

public class Main {
    public static void main(String[] args) {
        RandomArrayService randomArrayService = new RandomArrayService(GeneratorType.CAR_NUMBER);
        try {
            System.out.printf(randomArrayService.generateAsJson(5));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}