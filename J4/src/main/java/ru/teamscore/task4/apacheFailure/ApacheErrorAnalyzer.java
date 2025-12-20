package ru.teamscore.task4.apacheFailure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApacheErrorAnalyzer {

    private final Pattern pattern = Pattern.compile("^\\[(.+?)] \\[error] \\[client (.+?)] (.+)$");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss yyyy", Locale.ENGLISH);

    private final String resourceName;

    public ApacheErrorAnalyzer(String resourceName) {
        this.resourceName = resourceName;
    }

    public ApacheErrorAnalyzeResult analyze() {
        List<ApacheErrorItem> errorItemList = new ArrayList<>();
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream(resourceName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line = reader.readLine();
            while (line != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String dateTimeStr = matcher.group(1);
                    String clientIp = matcher.group(2);
                    String message = matcher.group(3);
                    if (message.contains("mod_jk"))
                        continue;
                    LocalDateTime ts = LocalDateTime.parse(dateTimeStr, formatter);
                    errorItemList.add(new ApacheErrorItem(message, clientIp, ts));
                }
                line = reader.readLine();
            }
            return new ApacheErrorAnalyzeResult(LocalDateTime.now(), errorItemList);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
