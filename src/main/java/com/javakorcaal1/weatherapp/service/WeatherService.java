package com.javakorcaal1.weatherapp.service;

import com.javakorcaal1.weatherapp.dao.CityDao;
import com.javakorcaal1.weatherapp.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {



    private CityDao cityDao;

    @Autowired
    public WeatherService(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    public void addCity(City city){

        cityDao.save(city);

    }

    public City getCityByName(String name){

        return cityDao.findCityByName(name);
    }

    public City getCityById(int id) throws CityNotFoundExeption {
        Optional<City> selectedCity = cityDao.findById(id);

        if (selectedCity.isPresent()){
            return selectedCity.get();
        }else{
            throw new CityNotFoundExeption("Qyteti nuk u gjend");
        }

    }

    public List<City> getAllCities(){

        List<City> cityList = new ArrayList<>();

        Iterable<City> cityIterable = cityDao.findAll();
        for (City city:cityIterable){

            cityList.add(city);
        }

        return cityList;

    }

    public void updateCityByName(String name, int date, double temperature, double windSpeed, double visibility, double humidity, String time) {
        City city = getCityByName(name);
        city.setDate(date);
        city.setTemperature(temperature);
        city.setWindSpeed(windSpeed);
        city.setVisibility(visibility);
        city.setHumidity(humidity);
        city.setTime(time);
        cityDao.save(city);
    }

    public void deleteCityById(int id){

        cityDao.deleteById(id);

    }

    public void deleteAllCities(){

        cityDao.deleteAll();

    }

}
