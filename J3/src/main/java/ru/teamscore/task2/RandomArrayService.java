package ru.teamscore.task2;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RandomArrayService {

    private final ArrayGenerator<?> generator;
    private final ObjectMapper mapper = new ObjectMapper();

    public RandomArrayService(GeneratorType type){
        switch (type){
            case BOOL:
                generator = new BooleanGenerator();
                break;
            case DICE:
                generator = new DiceGenerator();
                break;
            case CAR_NUMBER:
                generator = new CarNumberGenerator();
                break;
            default:
                throw new RuntimeException("Генератора с таким типом не существует! Тип: " + type);
        }
    }

    public String generateAsJson(int length) throws JsonProcessingException {
        Object[] array = generator.generationArray(length);
        return mapper.writeValueAsString(array);
    }
}
