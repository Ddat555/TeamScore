package org.example.task1;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

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
        return switch (month) {
            case JANUARY -> "январь";
            case FEBRUARY -> "февраль";
            case MARCH -> "март";
            case APRIL -> "апрель";
            case MAY -> "май";
            case JUNE -> "июнь";
            case JULY -> "июль";
            case AUGUST -> "август";
            case SEPTEMBER -> "сентябрь";
            case OCTOBER -> "октябрь";
            case NOVEMBER -> "ноябрь";
            case DECEMBER -> "декабрь";
        };
    }

    public int getCurrentMonthNumber(){
        var month = localDate.getMonth();
        return month.getValue();
    }

    public String getFirstDayMonthName(){
        var firstDay = localDate.withDayOfMonth(1);
        var dayOfWeek = firstDay.getDayOfWeek();
        return dayOfWeek.getDisplayName(TextStyle.SHORT, new Locale("ru"));
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
