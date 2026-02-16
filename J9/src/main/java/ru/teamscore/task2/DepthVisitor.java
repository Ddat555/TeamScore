package ru.teamscore.task2;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class DepthVisitor implements FileVisitor<Path> {

    private final Path root;
    private int maxDepth = 0;

    private DepthVisitor(Path startDir) {
        this.root = startDir;
    }

    private int getMaxDepth() {
        return maxDepth;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        if (!dir.equals(root)) {
            int curDepth = root.relativize(dir).getNameCount();
            if (curDepth > maxDepth) {
                maxDepth = curDepth;
            }
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        return FileVisitResult.CONTINUE;
    }

    public static int calculateMaxDepth(Path path) throws IOException {
        if (Files.notExists(path) || !Files.isDirectory(path)) {
            return -1;
        }

        DepthVisitor visitor = new DepthVisitor(path);
        Files.walkFileTree(path, visitor);
        return visitor.getMaxDepth();
    }
}
