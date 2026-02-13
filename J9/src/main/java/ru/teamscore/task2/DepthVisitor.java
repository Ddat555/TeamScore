package ru.teamscore.task2;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class DepthVisitor implements FileVisitor<Path> {

    private final Path root;
    private int maxDepth = 0;

    public DepthVisitor(Path root) {
        this.root = root;
    }

    public Path getRoot() {
        return root;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        int curDepth = root.relativize(dir).getNameCount();
        if(curDepth > maxDepth){
            maxDepth = curDepth;
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}
