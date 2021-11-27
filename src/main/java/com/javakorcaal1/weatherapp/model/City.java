package com.javakorcaal1.weatherapp.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "cities_table")
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20, unique = true, nullable = false)
    private String name ;

    private String country;

    private int date ;

    private double temperature;

    private double windSpeed;

    private double visibility;

    private double humidity;

    private double latitude;

    private double longtitude;

    private String refreshTime;

    private int countrycode;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_name" , referencedColumnName = "name")
    private List<FutureDay> futureDays = new ArrayList<>();

    public City() {
    }

    public City(String name, String country, int date, double temperature, double windSpeed, double visibility, double humidity, double latitude, double longtitude, String refreshTime, int countrycode, List<FutureDay> futureDays) {
        this.name = name;
        this.country = country;
        this.date = date;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.visibility = visibility;
        this.humidity = humidity;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.refreshTime = refreshTime;
        this.countrycode = countrycode;
        this.futureDays = futureDays;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public String getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(String refreshTime) {
        this.refreshTime = refreshTime;
    }

    public int getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(int countrycode) {
        this.countrycode = countrycode;
    }

    public List<FutureDay> getFutureDays() {
        return futureDays;
    }

    public void setFutureDays(List<FutureDay> futureDays) {
        this.futureDays = futureDays;
    }
}
