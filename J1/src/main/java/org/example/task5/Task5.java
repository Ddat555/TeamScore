package org.example.task5;

import java.time.LocalDateTime;

public class Task5 {

    public static void main(String[] args) {

        if (args.length != 0) {
            CountDown countDown = new CountDown(args[0]);
            System.out.println(countDown.execute());
        }
        else {
            CountDown countDown = new CountDown(LocalDateTime.of(2027,12,25,17,12,12));
            System.out.println(countDown.execute());
        }
    }
}
