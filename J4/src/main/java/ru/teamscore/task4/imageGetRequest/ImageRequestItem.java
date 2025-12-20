package ru.teamscore.task4.imageGetRequest;

public class ImageRequestItem {
    private final String host;
    private final String path;

    public ImageRequestItem(String host, String path) {
        this.host = host;
        this.path = path;
    }

    public String getHost() {
        return host;
    }

    public String getPath() {
        return path;
    }
}
