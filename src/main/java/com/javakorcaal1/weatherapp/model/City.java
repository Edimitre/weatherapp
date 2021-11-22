package com.javakorcaal1.weatherapp.model;


import javax.persistence.*;


@Entity
@Table(name = "cities_table")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(length = 20, unique = true, nullable = false)
    public String name ;

    public int date ;

    public double temperature;

    public double windSpeed;

    public double visibility;

    public double humidity;


    public City(String name, int date, double temperature, double windSpeed, double visibility, double humidity) {
        this.name = name;
        this.date = date;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.visibility = visibility;
        this.humidity = humidity;
    }

    public City() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", temperature=" + temperature +
                ", windSpeed=" + windSpeed +
                ", visibility=" + visibility +
                ", humidity=" + humidity +
                '}';
    }
}
