package ru.teamscore.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MOEXSearcherTest {

    private MOEXSearcher searcher;
    private static final String USER_HOME = System.getProperty("user.home");
    private static final Path OUTPUT_DIR = Paths.get(USER_HOME, "MOEX securities");

    @BeforeEach
    void setUp() throws IOException {
        searcher = new MOEXSearcher();
        if (Files.exists(OUTPUT_DIR)) {
            Files.list(OUTPUT_DIR)
                    .forEach(p -> {
                        try {
                            Files.delete(p);
                        } catch (IOException e) {
                        }
                    });
        }
    }

    @Test
    void testExtractTradedSecurities() {
        String json = "{\"securities\":{\"columns\":[\"secid\",\"shortname\",\"regnumber\",\"name\",\"isin\",\"is_traded\",\"emitent_id\",\"emitent_title\",\"emitent_inn\",\"emitent_okpo\"],\"data\":[[\"SBER\",\"Сбербанк\",\"10301481B\",\"Сбербанк России ПАО ао\",\"RU0009029540\",1,484,\"Публичное акционерное общество \\\"Сбербанк России\\\"\",\"7707083893\",\"00032537\"]]}}";

        List<Security> securities = searcher.extractTradedSecurities(json);

        assertFalse(securities.isEmpty());
        Security s = securities.get(0);
        assertEquals("SBER", s.getSecid());
        assertEquals("Сбербанк", s.getShortname());
        assertEquals("10301481B", s.getRegnumber());
        assertEquals("7707083893", s.getEmitentInn());
    }

    @Test
    void testExtractTradedSecurities_NoTraded() {
        String json = "{\"securities\":{\"columns\":[\"secid\",\"shortname\",\"regnumber\",\"name\",\"isin\",\"is_traded\",\"emitent_id\",\"emitent_title\",\"emitent_inn\",\"emitent_okpo\"],\"data\":[[\"SBER\",\"Сбербанк\",\"10301481B\",\"Сбербанк России ПАО ао\",\"RU0009029540\",0,484,\"Публичное акционерное общество \\\"Сбербанк России\\\"\",\"7707083893\",\"00032537\"]]}}";

        List<Security> securities = searcher.extractTradedSecurities(json);

        assertTrue(securities.isEmpty());
    }

    @Test
    void testSaveToCsv() throws IOException {
        Security s = new Security();
        s.setSecid("SBER");
        s.setShortname("Сбербанк");
        s.setRegnumber("10301481B");
        s.setName("Сбербанк России ПАО ао");
        s.setEmitentTitle("Публичное акционерное общество \"Сбербанк России\"");
        s.setEmitentInn("7707083893");
        s.setEmitentOkpo("00032537");

        var cf = searcher.saveToCsv("SBER", List.of(s));
        cf.join();

        Path testFile = OUTPUT_DIR.resolve("SBER.csv");

        assertTrue(Files.exists(testFile));
        String content = Files.readString(testFile);
        assertTrue(content.contains("SBER"));
        assertTrue(content.contains("Сбербанк"));
        assertTrue(content.contains("7707083893"));
    }

    @Test
    void testSearchAndSave() throws InterruptedException {
        searcher.searchAndSave("SBER");
        searcher.waitForAllTasks();
        Path expectedFile = OUTPUT_DIR.resolve("SBER.csv");
        assertTrue(Files.exists(expectedFile));
    }

    @Test
    void testConcurrentSearches() throws InterruptedException {
        searcher.searchAndSave("SBER");
        searcher.searchAndSave("GAZP");
        searcher.searchAndSave("Yandex");

        searcher.waitForAllTasks();

        assertTrue(Files.exists(OUTPUT_DIR.resolve("SBER.csv")));
        assertTrue(Files.exists(OUTPUT_DIR.resolve("GAZP.csv")));
        assertTrue(Files.exists(OUTPUT_DIR.resolve("Yandex.csv")));
    }
}