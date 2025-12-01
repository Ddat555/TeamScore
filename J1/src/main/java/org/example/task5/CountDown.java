package org.example.task5;

import java.time.Duration;
import java.time.LocalDateTime;

public class CountDown {

    private final LocalDateTime localDateTime;

    public CountDown() {
        this.localDateTime = LocalDateTime.now();
    }

    public CountDown(String dateString) {
        this.localDateTime = LocalDateTime.parse(dateString);
    }

    public CountDown(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String execute() {
        var nowDateTime = LocalDateTime.now();
        if (nowDateTime.isAfter(localDateTime) || nowDateTime.isEqual(localDateTime)) {
            return "Уже наступило";
        }

        Duration duration = Duration.between(nowDateTime, localDateTime);

        long seconds = duration.toSeconds();

        var days = seconds / (24 * 3600);
        var hours = (seconds % (24 * 3600)) / 3600;
        var minutes = (seconds % 3600) / 60;

        return parseToResult(days, hours, minutes);
    }

    private String parseToResult(long days, long hours, long minutes) {
        StringBuilder builder = new StringBuilder();
        if (days > 0) {
            builder.append(days).append(" ").append(getTimeForm(days, TimeForm.DAY)).append(" ");
        }
        if (hours > 0) {
            builder.append(hours).append(" ").append(getTimeForm(hours, TimeForm.HOUR)).append(" ");
        }
        if (minutes > 0) {
            builder.append(minutes).append(" ").append(getTimeForm(minutes, TimeForm.MINUTE)).append(" ");
        }


        return builder.toString();
    }

    private String getTimeForm(long number, TimeForm timeForm){
        long lastDigit = number % 10;
        long lastTwoDigits = number % 100;

        if (lastTwoDigits >= 11 && lastTwoDigits <= 14) {
            return timeForm.getForm3();

        }
        if (lastDigit == 1) {
            return timeForm.getForm1();

        }
        if (lastDigit >= 2 && lastDigit <= 4) {
            return timeForm.getForm2();
        }
        return timeForm.getForm3();
    }
}
