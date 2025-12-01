package org.example.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MonthInfoTest {

    private MonthInfo monthInfo;


    @Nested
    class November2023Test{
        @BeforeEach
        void setLocalDate(){
            monthInfo = new MonthInfo(LocalDate.of(2023,11,12));
        }

        @Test
        void getCurrentMonthName() {
            assertEquals("ноябрь", monthInfo.getCurrentMonthName());
        }

        @Test
        void getCurrentMonthNumber() {
            assertEquals(11, monthInfo.getCurrentMonthNumber());
        }

        @Test
        void getFirstDayMonthName() {
            assertEquals("ср", monthInfo.getFirstDayMonthName());
        }

        @Test
        void getLastDayMonth() {
            assertEquals("2023-11-30", monthInfo.getLastDayMonth().toString());
        }

        @Test
        void getLengthMonth() {
            assertEquals(30, monthInfo.getLengthMonth());
        }

        @Test
        void getQuarterWithYear() {
            assertEquals("2023 Q4", monthInfo.getQuarterWithYear());
        }

    }

    @Nested
    class January1900Test{
        @BeforeEach
        void setLocalDate(){
            monthInfo = new MonthInfo(LocalDate.of(1900,1,1));
        }

        @Test
        void getCurrentMonthName() {
            assertEquals("январь", monthInfo.getCurrentMonthName());
        }

        @Test
        void getCurrentMonthNumber() {
            assertEquals(1, monthInfo.getCurrentMonthNumber());
        }

        @Test
        void getFirstDayMonthName() {
            assertEquals("пн", monthInfo.getFirstDayMonthName());
        }

        @Test
        void getLastDayMonth() {
            assertEquals("1900-01-31", monthInfo.getLastDayMonth().toString());
        }

        @Test
        void getLengthMonth() {
            assertEquals(31, monthInfo.getLengthMonth());
        }

        @Test
        void getQuarterWithYear() {
            assertEquals("1900 Q1", monthInfo.getQuarterWithYear());
        }
    }

    @Nested
    class February2020Test{
        @BeforeEach
        void setLocalDate(){
            monthInfo = new MonthInfo(LocalDate.of(2020,2,12));
        }

        @Test
        void getCurrentMonthName() {
            assertEquals("февраль", monthInfo.getCurrentMonthName());
        }

        @Test
        void getCurrentMonthNumber() {
            assertEquals(2, monthInfo.getCurrentMonthNumber());
        }

        @Test
        void getFirstDayMonthName() {
            assertEquals("сб", monthInfo.getFirstDayMonthName());
        }

        @Test
        void getLastDayMonth() {
            assertEquals("2020-02-29", monthInfo.getLastDayMonth().toString());
        }

        @Test
        void getLengthMonth() {
            assertEquals(29, monthInfo.getLengthMonth());
        }

        @Test
        void getQuarterWithYear() {
            assertEquals("2020 Q1", monthInfo.getQuarterWithYear());
        }
    }

    @Nested
    class ConstructorWithoutParametersTest{
        @BeforeEach
        void setLocalDate(){
            monthInfo = new MonthInfo();
        }

        @Test
        void getCurrentMonthName() {
            assertEquals("декабрь", monthInfo.getCurrentMonthName());
        }

        @Test
        void getCurrentMonthNumber() {
            assertEquals(12, monthInfo.getCurrentMonthNumber());
        }

        @Test
        void getFirstDayMonthName() {
            assertEquals("пн", monthInfo.getFirstDayMonthName());
        }

        @Test
        void getLastDayMonth() {
            assertEquals("2025-12-31", monthInfo.getLastDayMonth().toString());
        }

        @Test
        void getLengthMonth() {
            assertEquals(31, monthInfo.getLengthMonth());
        }

        @Test
        void getQuarterWithYear() {
            assertEquals("2025 Q4", monthInfo.getQuarterWithYear());
        }
    }


}