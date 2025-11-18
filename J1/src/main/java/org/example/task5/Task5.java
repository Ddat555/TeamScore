package org.example.task5;

import java.time.LocalDateTime;

public class Task5 {

    public static void main(String[] args) {

//        CountDown countDown = new CountDown(LocalDateTime.of(2025,11,19,17,12,12));
        if(args.length != 0){
            CountDown countDown = new CountDown(args[0]);
            System.out.println(countDown.execute());
        }


    }
}
