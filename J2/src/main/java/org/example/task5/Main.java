package org.example.task5;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReadList readList = new ReadList();
        int position = 1;
        while (true) {
            System.out.printf("Position: %d Value: ", position);
            var value = scanner.next();
            try {
                readList.add(value);
                position++;
            } catch (AlreadyExistException e) {
                System.out.printf("Error! Value %s exist on position %d%n", e.getValue(), e.getPosition());
            }
        }
    }
}
