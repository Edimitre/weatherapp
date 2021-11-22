package com.javakorcaal1.weatherapp.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateService {


//    public static String getCurrentDate(){
//
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(dtf.format(now));
//        return dtf.format(now);
//    }

    public static String getCurrentHour(){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        return dtf.format(now);

    }

    public static int getTodayDate(){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
        LocalDateTime now = LocalDateTime.now();
        return Integer.parseInt(dtf.format(now));
    }
}
