package ru.teamscore.task2;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Main {

    public static void main(String[] args) {
        RandomArrayService randomArrayService = new RandomArrayService(GeneratorType.CAR_NUMBER);
        RandomArrayService randomArrayService2 = new RandomArrayService(GeneratorType.DICE);
        RandomArrayService randomArrayService3 = new RandomArrayService(GeneratorType.BOOL);
        try {
            System.out.println(randomArrayService.generateAsJson(5));
            System.out.println(randomArrayService2.generateAsJson(5));
            System.out.println(randomArrayService3.generateAsJson(5));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
