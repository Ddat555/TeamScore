package org.example.task4;

import java.time.LocalDate;

public class Task4 {

    public static void main(String[] args) {
        MonthInfo monthInfo = new MonthInfo();
        printInfo(monthInfo);
        MonthInfo monthInfo2 = new MonthInfo(LocalDate.of(2023,11,12));
        printInfo(monthInfo2);
        MonthInfo monthInfo3 = new MonthInfo(LocalDate.of(1900,1,1));
        printInfo(monthInfo3);
        MonthInfo monthInfo4 = new MonthInfo(LocalDate.of(2020,2,12));
        printInfo(monthInfo4);

    }

    private static void printInfo(MonthInfo monthInfo){
        System.out.println("CurrentMonthName: " + monthInfo.getCurrentMonthName());
        System.out.println("CurrentMonthNumber: " + monthInfo.getCurrentMonthNumber());
        System.out.println("FirstDayMonth: " + monthInfo.getFirstDayMonthName());
        System.out.println("LastDayMonth: " + monthInfo.getLastDayMonth());
        System.out.println("MonthDayLength: " + monthInfo.getLengthMonth());
        System.out.println("Quarter: " + monthInfo.getQuarterWithYear());
        System.out.println("---------------------------");
    }
}
