package ru.teamscore.task1;

public enum PathDifferenceStatus {
    NotExist,
    SameFile,
    BiggerFile,
    SmallerFile,
    SameSizeFile,
    SameDirectory,
    SameAbsoluteNameDepth,
    SamePrefix,
    SameRoot,
    Subpath,
    ParentPath
}
