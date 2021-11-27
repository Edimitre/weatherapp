package com.javakorcaal1.weatherapp.dao;

import com.javakorcaal1.weatherapp.model.FutureDay;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FutureDayDao extends CrudRepository<FutureDay, Integer> {


}
