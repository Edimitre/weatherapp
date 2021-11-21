package com.javakorcaal1.weatherapp;

import com.javakorcaal1.weatherapp.model.City;
import com.javakorcaal1.weatherapp.service.WeatherService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WeatherappApplicationTests {



	@Autowired
	private WeatherService service;

	@Test
	public  void addNewCity(){




		City city = new City("korce", 11,11,11,11,11);

		City p = service.addCity(city);
		Assertions.assertThat(p).isNotNull();
		Assertions.assertThat(p.getId()).isGreaterThan(0);
	}

}
