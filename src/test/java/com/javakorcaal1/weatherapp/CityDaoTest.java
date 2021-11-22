package com.javakorcaal1.weatherapp;

import com.javakorcaal1.weatherapp.api.WeatherApi;
import com.javakorcaal1.weatherapp.dao.CityDao;
import com.javakorcaal1.weatherapp.model.City;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.io.IOException;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
@SpringBootTest
class CityDaoTest {


    @Autowired
    private CityDao cityDao;

    @Test
    public void testAddNewCity(){

        City city = new City();
        city.setName("elbasan");

        try {
            City city1 = WeatherApi.getCityByApi(city.getName());

            City city2 = cityDao.save(city1);

            Assertions.assertThat(city2).isNotNull();
            Assertions.assertThat(city2.getId()).isGreaterThan(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
