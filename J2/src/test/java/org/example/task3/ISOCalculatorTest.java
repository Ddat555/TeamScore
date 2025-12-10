package org.example.task3;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ISOCalculatorTest {

    ISOCalculator isoCalculator = new ISOCalculator();

    @Test
    public void testWeek1_2024(){
        LocalDate[] dates = isoCalculator.calculateWeekDates(2024, 1);
        assertEquals(LocalDate.of(2024, 1, 1), dates[0]);
        assertEquals(LocalDate.of(2024, 1, 7), dates[1]);
    }

    @Test
    void testWeek53_2020(){
        LocalDate[] dates = isoCalculator.calculateWeekDates(2020, 53);
        assertEquals(LocalDate.of(2020, 12, 28), dates[0]);
        assertEquals(LocalDate.of(2021, 1, 3), dates[1]);
    }

    @Test
    void testWeek52_2023(){
        LocalDate[] dates = isoCalculator.calculateWeekDates(2023, 52);
        assertEquals(LocalDate.of(2023, 12, 25), dates[0]);
        assertEquals(LocalDate.of(2023, 12, 31), dates[1]);
    }

    @Test
    public void testWeek1_2025(){
        LocalDate[] dates = isoCalculator.calculateWeekDates(2025, 1);
        assertEquals(LocalDate.of(2024, 12, 30), dates[0]);
        assertEquals(LocalDate.of(2025, 1, 5), dates[1]);
    }

}