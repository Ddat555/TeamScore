package ru.teamscore.task4.apacheFailure;

import java.time.LocalDateTime;

public class ApacheErrorItem {
    private final String message;
    private final String clientIp;
    private final LocalDateTime localDateTime;

    public ApacheErrorItem(String message, String clientIp, LocalDateTime localDateTime) {
        this.message = message;
        this.clientIp = clientIp;
        this.localDateTime = localDateTime;
    }

    public String getMessage() {
        return message;
    }

    public String getClientIp() {
        return clientIp;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    @Override
    public String toString() {
        return "ApacheErrorItem{" +
                "message='" + message + '\'' +
                ", clientIp='" + clientIp + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
