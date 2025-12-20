package ru.teamscore.task4.imageGetRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageRequestAnalyzer {

    private final Pattern pattern = Pattern.compile("^(\\S+) - - \\[.+?] \"GET (\\S*?/images\\S*?) \\S+\" 200");

    private final String resourceName;

    public ImageRequestAnalyzer(String resourceName) {
        this.resourceName = resourceName;
    }

    public ImageRequestAnalyzeResult analyze() {
        List<ImageRequestItem> requestItemList = new ArrayList<>();
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream(resourceName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line = reader.readLine();
            while (line != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String host = matcher.group(1);
                    String path = matcher.group(2);
                    requestItemList.add(new ImageRequestItem(host, path));
                }
                line = reader.readLine();
            }
            return new ImageRequestAnalyzeResult(LocalDateTime.now(), requestItemList);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
