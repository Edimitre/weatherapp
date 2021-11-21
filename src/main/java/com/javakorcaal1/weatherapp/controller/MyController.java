package com.javakorcaal1.weatherapp.controller;

import com.javakorcaal1.weatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {


    @Autowired
    private WeatherService weatherService;


    @RequestMapping("/home")
    public String homePage(){
        return "index";
    }


    public static void saveCity(String city){
        System.out.println(city);
    }


}
