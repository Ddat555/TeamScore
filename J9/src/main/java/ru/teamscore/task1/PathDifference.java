package ru.teamscore.task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PathDifference {

    public static List<PathDifferenceStatus> difference(Path path1, Path path2) throws IOException {
        List<PathDifferenceStatus> pathDifferenceStatusList = new ArrayList<>();
        if (isNotExist(path1, path2)) {
            pathDifferenceStatusList.add(PathDifferenceStatus.NotExist);
            return pathDifferenceStatusList;
        }
        if (isSameFile(path1, path2))
            pathDifferenceStatusList.add(PathDifferenceStatus.SameFile);
        if (isBiggerFile(path1, path2))
            pathDifferenceStatusList.add(PathDifferenceStatus.BiggerFile);
        if (isSmallerFile(path1, path2))
            pathDifferenceStatusList.add(PathDifferenceStatus.SmallerFile);
        if (isSameSizeFile(path1, path2))
            pathDifferenceStatusList.add(PathDifferenceStatus.SameSizeFile);
        if (isSameDirectory(path1, path2))
            pathDifferenceStatusList.add(PathDifferenceStatus.SameDirectory);
        if (isSameAbsoluteNameDepth(path1, path2))
            pathDifferenceStatusList.add(PathDifferenceStatus.SameAbsoluteNameDepth);
        if (isSamePrefix(path1, path2))
            pathDifferenceStatusList.add(PathDifferenceStatus.SamePrefix);
        if (isSameRoot(path1, path2))
            pathDifferenceStatusList.add(PathDifferenceStatus.SameRoot);
        if (isSubpath(path1, path2))
            pathDifferenceStatusList.add(PathDifferenceStatus.Subpath);
        if (isParentPath(path1, path2))
            pathDifferenceStatusList.add(PathDifferenceStatus.ParentPath);
        return pathDifferenceStatusList;
    }

    public static boolean isNotExist(Path path1, Path path2) {
        return Files.notExists(path1) || Files.notExists(path2);
    }

    public static boolean isSameFile(Path path1, Path path2) throws IOException {
        return !isNotExist(path1, path2) && Files.isSameFile(path1, path2);
    }

    public static boolean isBiggerFile(Path path1, Path path2) throws IOException {
        return !isNotExist(path1, path2) &&
                (Files.size(path1) > Files.size(path2));
    }

    public static boolean isSmallerFile(Path path1, Path path2) throws IOException {
        return !isNotExist(path1, path2) &&
                (Files.size(path1) < Files.size(path2));
    }

    public static boolean isSameSizeFile(Path path1, Path path2) throws IOException {
        return !isNotExist(path1, path2) &&
                (Files.size(path1) == Files.size(path2));
    }

    public static boolean isSameDirectory(Path path1, Path path2) throws IOException {
        return !isNotExist(path1, path2) &&
                Files.isDirectory(path1) &&
                Files.isDirectory(path2) &&
                path1.toAbsolutePath().normalize().equals(path2.toAbsolutePath().normalize());
    }

    public static boolean isSameAbsoluteNameDepth(Path path1, Path path2) {
        return  path1.toAbsolutePath().getNameCount() == path2.toAbsolutePath().getNameCount();
    }

    public static boolean isSamePrefix(Path path1, Path path2) {
        return !isNotExist(path1, path2)
                && (path1.toAbsolutePath().getNameCount() > 0 ? path1.toAbsolutePath().getName(0) : path1)
                .equals(
                        (path2.toAbsolutePath().getNameCount() > 0 ? path2.toAbsolutePath().getName(0) : path2)
                );

    }

    public static boolean isSameRoot(Path path1, Path path2) {
        return (path1.toAbsolutePath().getRoot() != null ? path1.toAbsolutePath().getRoot() : path1)
                .equals(
                        path2.toAbsolutePath().getRoot() != null ? path2.toAbsolutePath().getRoot() : path2
                );
    }

    public static boolean isSubpath(Path path1, Path path2) {
        return !isNotExist(path1, path2) &&
                path2.startsWith(path1.normalize());
    }

    public static boolean isParentPath(Path path1, Path path2) {
        return !isNotExist(path1, path2) &&
                path1.startsWith(path2.normalize());
    }

}
