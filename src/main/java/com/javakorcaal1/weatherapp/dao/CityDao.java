package com.javakorcaal1.weatherapp.dao;


import com.javakorcaal1.weatherapp.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDao extends JpaRepository<City, Integer> {


}
