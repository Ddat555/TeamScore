package ru.teamscore.task4.linuxAuthenticationFailure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinuxAuthenticationFailureAnalyzer {
    private final String resourceName;
    Pattern pattern = Pattern.compile(
            "(\\w{3})\\s+(\\d{1,2}).*authentication failure.*rhost=([^\\s]+)"
    );
    private final Map<String, Month> MONTHS = Map.ofEntries(
            Map.entry("Jan", Month.JANUARY),
            Map.entry("Feb", Month.FEBRUARY),
            Map.entry("Mar", Month.MARCH),
            Map.entry("Apr", Month.APRIL),
            Map.entry("May", Month.MAY),
            Map.entry("Jun", Month.JUNE),
            Map.entry("Jul", Month.JULY),
            Map.entry("Aug", Month.AUGUST),
            Map.entry("Sep", Month.SEPTEMBER),
            Map.entry("Oct", Month.OCTOBER),
            Map.entry("Nov", Month.NOVEMBER),
            Map.entry("Dec", Month.DECEMBER)
    );

    public LinuxAuthenticationFailureAnalyzer(String resourceName) {
        this.resourceName = resourceName;
    }

    public LinuxAuthenticationFailureAnalyzeResult analyze() {
        Map<LocalDate, Map<String, Integer>> stats = new HashMap<>();
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream(resourceName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line = reader.readLine();
            while (line != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String month = matcher.group(1);
                    int day = Integer.parseInt(matcher.group(2));
                    if (day < 1 || day > 31)
                        continue;
                    String host = matcher.group(3);

                    LocalDate localDate = LocalDate.of(
                            Year.now().getValue(),
                            MONTHS.get(month),
                            day
                    );

                    stats.computeIfAbsent(localDate, d -> new HashMap<>())
                            .merge(host, 1, Integer::sum);
                }
                line = reader.readLine();
            }
            System.out.println("ВОЗВАРТ");
            return new LinuxAuthenticationFailureAnalyzeResult(LocalDateTime.now(), stats);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
