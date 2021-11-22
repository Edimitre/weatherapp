package com.javakorcaal1.weatherapp.dao;


import com.javakorcaal1.weatherapp.model.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDao extends CrudRepository<City, Integer> {


    @Query(value = "select * from cities_table where name = :name", nativeQuery = true)
    City findCityByName(@Param("name") String name);

}

