package com.javakorcaal1.weatherapp.service;

import com.javakorcaal1.weatherapp.dao.FutureDayDao;

import com.javakorcaal1.weatherapp.model.FutureDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class FutureDayService {

    private FutureDayDao futureDayDao;

    @Autowired
    public FutureDayService(FutureDayDao futureDayDao) {
        this.futureDayDao = futureDayDao;
    }

    public void addFutureDay(FutureDay futureDay){

        futureDayDao.save(futureDay);

    }

    public List<FutureDay> getCityFutureDays(){

        List<FutureDay> futureDayList = new ArrayList<>();

        Iterable<FutureDay> futureDaysIterable = futureDayDao.findAll();
        for (FutureDay futureDay:futureDaysIterable){

            futureDayList.add(futureDay);
        }

        return futureDayList;

    }

    public void deleteAllFutureDays(){

        futureDayDao.deleteAll();

    }

    public List<FutureDay> saveFutureDaysList(List<FutureDay> futureDays){
        futureDayDao.saveAll(futureDays);
        return futureDays;
    }

}
