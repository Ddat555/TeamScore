package org.example.task5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadList {
    private final List<String> stringList = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public void add(String value) throws AlreadyExistException {
        if (stringList.contains(value)) {
            throw new AlreadyExistException(value, stringList.indexOf(value) + 1);
        }
        stringList.add(value);
    }
}
