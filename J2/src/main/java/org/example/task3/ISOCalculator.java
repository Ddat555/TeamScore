package org.example.task3;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;

public class ISOCalculator {

    public LocalDate[] calculateWeekDates(int year, int weekNumber) {
        LocalDate jan4 = LocalDate.of(year, 1, 4);
        LocalDate firstMonday = jan4.with(DayOfWeek.MONDAY);

        LocalDate targetMonday = firstMonday.plusWeeks(weekNumber - 1);
        LocalDate targetSunday = targetMonday.plusDays(6);

        return new LocalDate[]{targetMonday, targetSunday};
    }
}
