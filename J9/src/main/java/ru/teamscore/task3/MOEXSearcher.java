package ru.teamscore.task3;

import com.jayway.jsonpath.JsonPath;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MOEXSearcher {

    private static final String MOEX_API_URL = "https://iss.moex.com/iss/securities.json?q=";
    private static final String USER_HOME = System.getProperty("user.home");
    private static final Path OUTPUT_DIR = Paths.get(USER_HOME, "MOEX securities");
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ConcurrentLinkedQueue<CompletableFuture<Void>> downloadTasks = new ConcurrentLinkedQueue<>();

    public MOEXSearcher() throws IOException {
        if (!Files.exists(OUTPUT_DIR)) {
            Files.createDirectory(OUTPUT_DIR);
        }
    }

    public void searchAndSave(String query) {
        System.out.println("Поиск...");
        String url = MOEX_API_URL + query.replace(" ", "%20");
        System.out.println(url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .build();

        CompletableFuture<Void> task = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(this::extractTradedSecurities)
                .thenCompose(securities -> saveToCsv(query, securities))
                .exceptionally(ex -> {
                    System.out.println("Ошибка");
                    return null;
                });
        downloadTasks.add(task);
    }

    public List<Security> extractTradedSecurities(String json) {
        List<Security> result = new ArrayList<>();
        System.out.println("Получил JSON " + json);
        List<String> secids = JsonPath.read(json, "$.securities.data[*].[0]");
        List<String> shortNames = JsonPath.read(json, "$.securities.data[*].[1]");
        List<String> regNumbers = JsonPath.read(json, "$.securities.data[*].[2]");
        List<String> names = JsonPath.read(json, "$.securities.data[*].[3]");
        List<String> isTradedList = JsonPath.read(json, "$.securities.data[*].[5]");
        List<String> emitentTitles = JsonPath.read(json, "$.securities.data[*].[7]");
        List<String> emitentInns = JsonPath.read(json, "$.securities.data[*].[8]");
        List<String> emitentOkpos = JsonPath.read(json, "$.securities.data[*].[9]");

        for (int i = 0; i < secids.size(); i++) {
            if (i < isTradedList.size() && "1".equals(String.valueOf(isTradedList.get(i)))) {
                Security sec = new Security();
                sec.setSecid(secids.get(i));
                sec.setShortname(shortNames.get(i));
                sec.setRegnumber(regNumbers.get(i));
                sec.setName(names.get(i));
                sec.setEmitentTitle(emitentTitles.get(i));
                sec.setEmitentInn(emitentInns.get(i));
                sec.setEmitentOkpo(emitentOkpos.get(i));
                result.add(sec);
            }
        }
        System.out.println(result.size());
        return result;
    }

    public CompletableFuture<Void> saveToCsv(String query, List<Security> securities) {
        return CompletableFuture.runAsync(() -> {
            if (securities.isEmpty())
                return;
            Path csvFile = OUTPUT_DIR.resolve(query + ".csv");
            try (Writer writer = Files.newBufferedWriter(csvFile, StandardCharsets.UTF_8)) {
                ColumnPositionMappingStrategy<Security> strategy = new ColumnPositionMappingStrategy<>();
                strategy.setType(Security.class);
                String[] columns = {"secid", "shortname", "regnumber", "name", "emitentTitle", "emitentInn", "emitentOkpo"};
                strategy.setColumnMapping(columns);

                StatefulBeanToCsv<Security> beanToCsv = new StatefulBeanToCsvBuilder<Security>(writer)
                        .withMappingStrategy(strategy)
                        .build();

                beanToCsv.write(securities);
                System.out.println("Файл сохранен: " + csvFile);

            } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void waitForAllTasks() {
        System.out.println("Ожидаю завершения...");
        CompletableFuture<Void> all = CompletableFuture.allOf(
                downloadTasks.toArray(new CompletableFuture[0])
        );
        all.join();
        System.out.println("Завершено");
    }
}
