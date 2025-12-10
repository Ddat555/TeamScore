package org.example.task3;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class InputOutputTest {

    InputOutput inputOutput = new InputOutput();

    @Test
    void validationInputData() {
        assertTrue(inputOutput.validationInputData(2025, 1));
        assertFalse(inputOutput.validationInputData(0, 1));
        assertFalse(inputOutput.validationInputData(2025, 54));

    }

    @Test
    void formatDateRange() {
        String formatedDate = inputOutput.formatDateRange(LocalDate.of(2024,12,30), LocalDate.of(2025,1,5));
        String expected  = "2024-12-30 – 2025-01-05";
        assertEquals(expected, formatedDate);
    }
}