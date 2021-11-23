package com.javakorcaal1.weatherapp.controller;

import com.javakorcaal1.weatherapp.api.WeatherApi;
import com.javakorcaal1.weatherapp.model.City;
import com.javakorcaal1.weatherapp.service.DateService;
import com.javakorcaal1.weatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class MyController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/home")
    public String homePage(Model model){
        model.addAttribute("city", new City());
        return "index";
    }

    @GetMapping("/searchCity")
    public String searchCity(Model model) throws IOException {
        model.addAttribute("city", new City());
        return "search_form";
    }

    @RequestMapping(value = "/addCityToDatabase", method = RequestMethod.POST)
    public String addCityToDatabase(Model model, @RequestParam String name) throws IOException{

        // kontrollo ne database
        City selectedCity = weatherService.getCityByName(name);

        // nqs qyteti nuk ekziston ne database
        if (selectedCity==null){
            System.out.println("nuk eshte city ne database");
            // e mar nga api edhe e vendos ne database
            try {
                selectedCity = WeatherApi.getCityByApi(name);
                if (selectedCity != null){
                    weatherService.addCity(selectedCity);
                    System.out.println("qyeteti u mor nga api");
                    model.addAttribute("city", selectedCity);
                    return "city_weather_details";
                }else{
                    System.out.println("qyteti nuk u gjend");
                    return "redirect:/searchCity";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "city_weather_details";
        }else {
            if (selectedCity.getDate()!=DateService.getTodayDate()){

                selectedCity = WeatherApi.getCityByApi(selectedCity.getName());

                weatherService.updateCityByName(selectedCity.getName(), selectedCity.getDate(),
                        selectedCity.getTemperature(), selectedCity.getWindSpeed(),
                        selectedCity.getVisibility(), selectedCity.getHumidity(), selectedCity.getTime());

                model.addAttribute("city", selectedCity);

            }

            model.addAttribute("city", selectedCity);
            return "city_weather_details";
        }


    }

    @RequestMapping("/cities")
    public String showAllCities(Model model){
        List<City> allCitiesList = weatherService.getAllCities();
        model.addAttribute("allCitiesList", allCitiesList);
        return "all_cities";
    }

    @RequestMapping("/deleteAll")
    public String deleteAll(Model model) {
        List<City> allCitiesList = weatherService.getAllCities();

        weatherService.deleteAllCities();

        model.addAttribute("allCitiesList", allCitiesList);
        return "all_cities";
    }


    @RequestMapping(value = "/city/delete{name}")
    public String delete(@PathVariable String name,Model model){

        City city = weatherService.getCityByName(name);
        int id  = city.getId();
        weatherService.deleteCityById(id);


        List<City> allCitiesList = weatherService.getAllCities();

        model.addAttribute("allCitiesList", allCitiesList);
        return "all_cities";

    }

}
