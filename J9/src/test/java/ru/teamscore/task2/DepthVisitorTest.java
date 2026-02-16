package ru.teamscore.task2;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DepthVisitorTest {

    @Test
    void testDirectoryWithSubfolders() throws IOException {
        Path dir = Paths.get("test-files/dir1");
        int depth = DepthVisitor.calculateMaxDepth(dir);
        assertEquals(1, depth);
    }

    @Test
    void testDirectoryWithoutSubfolders() throws IOException {
        Path dir = Paths.get("test-files/dir2");
        int depth = DepthVisitor.calculateMaxDepth(dir);
        assertEquals(0, depth);
    }

    @Test
    void testEmptyDirectory() throws IOException {
        Path dir = Paths.get("test-files/dir3");
        int depth = DepthVisitor.calculateMaxDepth(dir);
        assertEquals(0, depth);
    }

    @Test
    void testFileInsteadOfDirectory() throws IOException {
        Path file = Paths.get("test-files/fileA.txt");

        int depth = DepthVisitor.calculateMaxDepth(file);

        assertEquals(-1, depth);
    }

    @Test
    void testNonExistentPath() throws IOException {
        Path notExists = Paths.get("test-files/not_exists.txt");

        int depth = DepthVisitor.calculateMaxDepth(notExists);

        assertEquals(-1, depth);
    }
}