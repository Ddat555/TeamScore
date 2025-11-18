package org.example.task4;

import java.time.LocalDate;

public class MonthInfo {

    private final LocalDate localDate;

    public MonthInfo() {
        this.localDate = LocalDate.now();
    }

    public MonthInfo(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getCurrentMonthName(){
        var month = localDate.getMonth();
        return month.name();
    }

    public int getCurrentMonthNumber(){
        var month = localDate.getMonth();
        return month.getValue();
    }

    public String getFirstDayMonthName(){
        var firstDay = localDate.withDayOfMonth(1);
        var dayOfWeek = firstDay.getDayOfWeek();
        return dayOfWeek.name();
    }

    public LocalDate getLastDayMonth(){
        return localDate.withDayOfMonth(localDate.lengthOfMonth());
    }

    public int getLengthMonth(){
        return localDate.lengthOfMonth();
    }

    public String getQuarterWithYear(){
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        int quarter = (month - 1) / 3 + 1;
        return year + " Q" + quarter;
    }
}
