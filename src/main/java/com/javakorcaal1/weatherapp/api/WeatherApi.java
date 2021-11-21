package com.javakorcaal1.weatherapp.api;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.javakorcaal1.weatherapp.model.City;
import com.javakorcaal1.weatherapp.service.DateService;
import com.javakorcaal1.weatherapp.service.WeatherService;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.logging.Logger;

public class WeatherApi {




    private final  static String API_KEY = "44ca06d59a61e9757947ced106f9c59b";
    private static HttpURLConnection connection;
//    String byCityName = "api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";


    public static void main(String[] args) throws IOException {
        getCityWeatherByName("korce");
    }

    public static void getCityWeatherByName(String name) throws IOException {



              // nqs lidhja qe beme me serverin e api ishte ok
              if (isConnectionOk(name)){

                  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                  String inputLine = null;

                  StringBuilder response = new StringBuilder();
                  while(true){
                      try {
                          if ((inputLine = bufferedReader.readLine()) == null) break;
                      } catch (IOException e) {
                          e.printStackTrace();
                      }


                      response.append(inputLine);

                      JSONObject myResponse = new JSONObject(response.toString());


//                      System.out.println(myResponse.get("wind"));




                      double visibility = Double.parseDouble(myResponse.get("visibility").toString());

                      JSONObject wind = (JSONObject) myResponse.get("wind");

                      String windSped = wind.get("speed").toString();

                      double windSpeed = Double.parseDouble(windSped);

                      JSONObject myObject = (JSONObject) myResponse.get("main");




                      Object temp = myObject.get("temp");
                      double temperature =  Double.parseDouble(temp.toString());
//
//                      Object temp_min = myObject.get("temp_min");
//                      double min_temp =  Double.parseDouble(temp_min.toString());
//
//                      Object temp_max = myObject.get("temp_max");
//                      double max_temp =  Double.parseDouble(temp_min.toString());

                      Object humidity1 = myObject.get("humidity");
                      double humidity =  Double.parseDouble(humidity1.toString());


                      City city = new City(name, DateService.getTodayDate(), temperature, windSpeed , visibility, humidity);

                      WeatherService weatherService = new WeatherService();
                      weatherService.addCity(city);
                  }

                  bufferedReader.close();
              }
    }



    public static boolean isConnectionOk(String cityName){

        int status = 0;
        String byCityName = "https://api.openweathermap.org/data/2.5/weather?q="+cityName+"&units=metric&lang=al&appid="+API_KEY+"";


        try {
            URL url = new URL(byCityName);
            // request set up
            try {
                connection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                connection.setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            connection.setConnectTimeout(5000);


            try {
                status = connection.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if(status == 200){
            return true;
        }else {

            return false;
        }
    }
}
