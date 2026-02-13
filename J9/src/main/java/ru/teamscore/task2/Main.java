package ru.teamscore.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Укажите путь к директории в качестве аргумента");
            return;
        }

        Path path = Paths.get(args[0]);

        if (!Files.exists(path)) {
            System.out.println(-1);
            return;
        }

        if (!Files.isDirectory(path)) {
            System.out.println("This is not a directory");
            return;
        }

        DepthVisitor visitor = new DepthVisitor(path);

        try {
            Files.walkFileTree(path, visitor);
            System.out.println(visitor.getMaxDepth());
        } catch (IOException e) {
            System.err.println("Ошибка при обходе директории: " + e.getMessage());
            System.out.println(-1);
        }
    }
}
