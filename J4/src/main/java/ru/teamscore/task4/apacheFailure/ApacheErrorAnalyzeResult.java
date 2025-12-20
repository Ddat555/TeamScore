package ru.teamscore.task4.apacheFailure;

import java.time.LocalDateTime;
import java.util.List;

public class ApacheErrorAnalyzeResult {
    private final LocalDateTime created;
    private final List<ApacheErrorItem> errorItemList;

    public ApacheErrorAnalyzeResult(LocalDateTime created, List<ApacheErrorItem> errorItemList) {
        this.created = created;
        this.errorItemList = errorItemList;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public List<ApacheErrorItem> getErrorItemList() {
        return errorItemList;
    }

    @Override
    public String toString() {
        return "ApacheErrorAnalyzeResult{" +
                "created=" + created +
                ", errorItemList=" + errorItemList +
                '}';
    }
}
