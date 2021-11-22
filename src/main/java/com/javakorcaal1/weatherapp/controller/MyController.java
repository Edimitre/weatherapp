package com.javakorcaal1.weatherapp.controller;

import com.javakorcaal1.weatherapp.api.WeatherApi;
import com.javakorcaal1.weatherapp.dao.CityDao;
import com.javakorcaal1.weatherapp.model.City;
import com.javakorcaal1.weatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class MyController {

    @Autowired
    private WeatherService weatherService;


    @GetMapping("/home")
    public String homePage(Model model){
        model.addAttribute("city", new City());
        return "index";
    }


    @GetMapping("/fillCity")
    public String fillCity(Model model) throws IOException {
        model.addAttribute("city", new City());
        return "test";
    }

    @RequestMapping(value = "/addCityToDatabase", method = RequestMethod.POST)
    public String addCityToDatabase(Model model, @RequestParam String name) {

        // kontrollo ne database
        City selectedCity = weatherService.getCityByName(name);

        // nqs qyteti nuk ekziston ne database
        if (selectedCity==null){
            // e mar nga api edhe e vendos ne database
            try {
                selectedCity = WeatherApi.getCityByApi(name);
                if (selectedCity != null){
                    weatherService.addCity(selectedCity);
                    System.out.println("qyeteti u shtua");
                    model.addAttribute("city", selectedCity);
                    return "city_weather_details";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("qyteti nuk u gjend");


            return "redirect:/home";

        }else {
            model.addAttribute("city", selectedCity);
            return "city_weather_details";
        }

    }


    @RequestMapping("/cityDetails")
    public String showCityDetails(Model model, City city){
        model.addAttribute("city", city);
        return "city_weather_details";
    }
}
