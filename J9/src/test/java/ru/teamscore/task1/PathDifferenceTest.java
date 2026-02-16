package ru.teamscore.task1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PathDifferenceTest {

    private static Path TEST_DIR;
    private static Path DIR1;
    private static Path DIR2;
    private static Path FILE1_DIR1;
    private static Path FILE2_DIR1;
    private static Path FILE1_DIR2;
    private static Path FILEA;
    private static Path FILEB;
    private static Path NOT_EXISTS;

    @BeforeAll
    static void setUp() {
        TEST_DIR = Paths.get("test-files");
        DIR1 = TEST_DIR.resolve("dir1");
        DIR2 = TEST_DIR.resolve("dir2");
        FILE1_DIR1 = DIR1.resolve("file1.txt");
        FILE2_DIR1 = DIR1.resolve("file2.txt");
        FILE1_DIR2 = DIR2.resolve("file1.txt");
        FILEA = TEST_DIR.resolve("fileA.txt");
        FILEB = TEST_DIR.resolve("fileB.txt");
        NOT_EXISTS = TEST_DIR.resolve("not_exists.txt");
    }

    @Test
    void testNotExist() {
        assertTrue(PathDifference.isNotExist(NOT_EXISTS, FILEA));
        assertTrue(PathDifference.isNotExist(FILEA, NOT_EXISTS));
        assertFalse(PathDifference.isNotExist(FILEA, FILEB));
    }

    @Test
    void testSameFile() throws IOException {
        assertTrue(PathDifference.isSameFile(FILEA, FILEA));
        assertFalse(PathDifference.isSameFile(FILEA, FILEB));
    }

    @Test
    void testBiggerFile() throws IOException {
        assertTrue(PathDifference.isBiggerFile(FILEB, FILEA));
        assertFalse(PathDifference.isBiggerFile(FILEA, FILEB));
    }

    @Test
    void testSmallerFile() throws IOException {
        assertTrue(PathDifference.isSmallerFile(FILEA, FILEB));
        assertFalse(PathDifference.isSmallerFile(FILEB, FILEA));
    }

    @Test
    void testSameSizeFile() throws IOException {
        assertFalse(PathDifference.isSameSizeFile(FILEA, FILEB));
    }

    @Test
    void testSameDirectory() throws IOException {
        assertTrue(PathDifference.isSameDirectory(DIR1, DIR1));
        assertFalse(PathDifference.isSameDirectory(DIR1, DIR2));
        assertFalse(PathDifference.isSameDirectory(DIR1, FILE1_DIR1));
    }

    @Test
    void testSameAbsoluteNameDepth() {
        assertTrue(PathDifference.isSameAbsoluteNameDepth(FILE1_DIR1, FILE1_DIR2));
        assertFalse(PathDifference.isSameAbsoluteNameDepth(FILE1_DIR1, FILEA));
    }

    @Test
    void testSamePrefix() {
        assertTrue(PathDifference.isSamePrefix(FILE1_DIR1, FILE2_DIR1));
        assertTrue(PathDifference.isSamePrefix(FILE1_DIR1, FILE2_DIR1));
        assertTrue(PathDifference.isSamePrefix(FILE1_DIR1, FILE1_DIR2));
    }

    @Test
    void testSameRoot() {
        assertTrue(PathDifference.isSameRoot(FILEA, FILEB));
        assertTrue(PathDifference.isSameRoot(DIR1, FILEA));
    }

    @Test
    void testSubpath() {
        assertTrue(PathDifference.isSubpath(DIR1, FILE1_DIR1));
        assertTrue(PathDifference.isSubpath(DIR1, FILE2_DIR1));
        assertFalse(PathDifference.isSubpath(DIR1, FILE1_DIR2));
    }

    @Test
    void testParentPath() {
        assertTrue(PathDifference.isParentPath(FILE1_DIR1, DIR1));
        assertFalse(PathDifference.isParentPath(FILEA, DIR1));
    }

    @Test
    void testDifference() throws IOException {
        List<PathDifferenceStatus> statuses = PathDifference.difference(FILEA, FILEA);

        assertTrue(statuses.contains(PathDifferenceStatus.SameFile));
        assertTrue(statuses.contains(PathDifferenceStatus.SameSizeFile));
        assertTrue(statuses.contains(PathDifferenceStatus.SameRoot));

        statuses = PathDifference.difference(FILEB, FILEA);
        assertTrue(statuses.contains(PathDifferenceStatus.BiggerFile));
        assertFalse(statuses.contains(PathDifferenceStatus.SameFile));
    }
}