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
            builder.append(days).append(" ").append(getDayWord(days)).append(" ");
        }
        if (hours > 0) {
            builder.append(hours).append(" ").append(getHourWord(hours)).append(" ");
        }
        if (minutes > 0) {
            builder.append(minutes).append(" ").append(getMinuteWord(minutes)).append(" ");
        }


        return builder.toString();
    }

    private String getDayWord(long number) {
        long lastDigit = number % 10;
        long lastTwoDigits = number % 100;

        if (lastTwoDigits >= 11 && lastTwoDigits <= 14) {
            return "дней";
        }
        if (lastDigit == 1) {
            return "день";
        }
        if (lastDigit >= 2 && lastDigit <= 4) {
            return "дня";
        }
        return "дней";
    }

    private String getHourWord(long number) {
        long lastDigit = number % 10;
        long lastTwoDigits = number % 100;

        if (lastTwoDigits >= 11 && lastTwoDigits <= 14) {
            return "часов";
        }
        if (lastDigit == 1) {
            return "час";
        }
        if (lastDigit >= 2 && lastDigit <= 4) {
            return "часа";
        }
        return "часов";
    }

    private String getMinuteWord(long number) {
        long lastDigit = number % 10;
        long lastTwoDigits = number % 100;

        if (lastTwoDigits >= 11 && lastTwoDigits <= 14) {
            return "минут";
        }
        if (lastDigit == 1) {
            return "минута";
        }
        if (lastDigit >= 2 && lastDigit <= 4) {
            return "минуты";
        }
        return "минут";
    }


}
