package ru.teamscore.task4.linuxAuthenticationFailure;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class LinuxAuthenticationFailureAnalyzeResult {
    private final LocalDateTime timeCreate;
    private final Map<LocalDate, Map<String, Integer>> stats;

    public LinuxAuthenticationFailureAnalyzeResult(LocalDateTime timeCreate, Map<LocalDate, Map<String, Integer>> stats) {
        this.timeCreate = timeCreate;
        this.stats = stats;
    }

    public LocalDateTime getTimeCreate() {
        return timeCreate;
    }

    public Map<LocalDate, Map<String, Integer>> getStats() {
        return stats;
    }

    @Override
    public String toString() {
        return "LinuxAuthenticationFailureAnalyzeResult{" +
                "timeCreate=" + timeCreate +
                ", stats=" + stats +
                '}';
    }
}
