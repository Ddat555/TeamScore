package ru.teamscore.task4.imageGetRequest;

import java.time.LocalDateTime;
import java.util.List;

public class ImageRequestAnalyzeResult {
    private final LocalDateTime created;
    private final List<ImageRequestItem> requestItems;

    public ImageRequestAnalyzeResult(LocalDateTime created, List<ImageRequestItem> requestItems) {
        this.created = created;
        this.requestItems = requestItems;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public List<ImageRequestItem> getRequestItems() {
        return requestItems;
    }
}
