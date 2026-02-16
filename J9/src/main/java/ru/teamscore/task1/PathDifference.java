package ru.teamscore.task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PathDifference {

    public static List<PathDifferenceStatus> difference(Path path1, Path path2) throws IOException {
        List<PathDifferenceStatus> statuses = new ArrayList<>();

        if (isNotExist(path1, path2)) {
            statuses.add(PathDifferenceStatus.NotExist);
        }

        if (isSameFile(path1, path2)) {
            statuses.add(PathDifferenceStatus.SameFile);
        }

        if (isBiggerFile(path1, path2)) {
            statuses.add(PathDifferenceStatus.BiggerFile);
        }

        if (isSmallerFile(path1, path2)) {
            statuses.add(PathDifferenceStatus.SmallerFile);
        }

        if (isSameSizeFile(path1, path2)) {
            statuses.add(PathDifferenceStatus.SameSizeFile);
        }

        if (isSameDirectory(path1, path2)) {
            statuses.add(PathDifferenceStatus.SameDirectory);
        }

        if (isSameAbsoluteNameDepth(path1, path2)) {
            statuses.add(PathDifferenceStatus.SameAbsoluteNameDepth);
        }

        if (isSamePrefix(path1, path2)) {
            statuses.add(PathDifferenceStatus.SamePrefix);
        }

        if (isSameRoot(path1, path2)) {
            statuses.add(PathDifferenceStatus.SameRoot);
        }

        if (isSubpath(path1, path2)) {
            statuses.add(PathDifferenceStatus.Subpath);
        }

        if (isParentPath(path1, path2)) {
            statuses.add(PathDifferenceStatus.ParentPath);
        }

        return statuses;
    }

    public static boolean isNotExist(Path path1, Path path2) {
        return Files.notExists(path1) || Files.notExists(path2);
    }

    public static boolean isSameFile(Path path1, Path path2) throws IOException {
        return !isNotExist(path1, path2) && Files.isSameFile(path1, path2);
    }

    public static boolean isBiggerFile(Path path1, Path path2) throws IOException {
        if (isNotExist(path1, path2) || Files.isDirectory(path1) || Files.isDirectory(path2)) {
            return false;
        }
        return Files.size(path1) > Files.size(path2);
    }

    public static boolean isSmallerFile(Path path1, Path path2) throws IOException {
        if (isNotExist(path1, path2) || Files.isDirectory(path1) || Files.isDirectory(path2)) {
            return false;
        }
        return Files.size(path1) < Files.size(path2);
    }

    public static boolean isSameSizeFile(Path path1, Path path2) throws IOException {
        if (isNotExist(path1, path2) || Files.isDirectory(path1) || Files.isDirectory(path2)) {
            return false;
        }
        return Files.size(path1) == Files.size(path2);
    }

    public static boolean isSameDirectory(Path path1, Path path2) throws IOException {
        return !isNotExist(path1, path2) &&
                Files.isDirectory(path1) &&
                Files.isDirectory(path2) &&
                Files.isSameFile(path1, path2);
    }

    public static boolean isSameAbsoluteNameDepth(Path path1, Path path2) {
        return path1.toAbsolutePath().getNameCount() == path2.toAbsolutePath().getNameCount();
    }

    public static boolean isSamePrefix(Path path1, Path path2) {
        Path absPath1 = path1.toAbsolutePath();
        Path absPath2 = path2.toAbsolutePath();

        if (absPath1.getNameCount() > 0 && absPath2.getNameCount() > 0) {
            return absPath1.getName(0).equals(absPath2.getName(0));
        }
        return false;
    }

    public static boolean isSameRoot(Path path1, Path path2) {
        Path root1 = path1.toAbsolutePath().getRoot();
        Path root2 = path2.toAbsolutePath().getRoot();

        if (root1 != null && root2 != null) {
            return root1.equals(root2);
        }
        return root1 == null && root2 == null;
    }

    public static boolean isSubpath(Path path1, Path path2) {
        return !isNotExist(path1, path2) && path2.startsWith(path1.normalize());
    }

    public static boolean isParentPath(Path path1, Path path2) {
        return !isNotExist(path1, path2) && path1.startsWith(path2.normalize());
    }
}