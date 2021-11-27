package com.javakorcaal1.weatherapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FutureDay {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String date;

    private double temperature;

    private double feelsLike;

    private double humidity;

    private double windSpeed;

    public FutureDay() {
    }

    public FutureDay(String date, double temperature, double feelsLike, double humidity, double windSpeed) {
        this.date = date;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.windSpeed = windSpeed;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    public String toString() {
        return "FutureDay{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", temperature=" + temperature +
                ", feelsLike=" + feelsLike +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                '}';
    }
}
