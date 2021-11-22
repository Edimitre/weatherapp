package com.javakorcaal1.weatherapp.api;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.javakorcaal1.weatherapp.model.City;
import com.javakorcaal1.weatherapp.service.DateService;
import com.javakorcaal1.weatherapp.service.WeatherService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;


public class WeatherApi {




    private final  static String API_KEY = "44ca06d59a61e9757947ced106f9c59b";
    private static HttpURLConnection connection;


//    String byCityName = "api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";



    @Autowired
    public static WeatherService weatherService;

    public static City getCityByApi(String name) throws IOException {

        int status = 0;
        String byCityName = "https://api.openweathermap.org/data/2.5/weather?q="+name+"&units=metric&lang=al&appid="+API_KEY+"";


        URL url = new URL(byCityName);

        connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        status = connection.getResponseCode();

        connection.setConnectTimeout(5000);





        City city = new City();


              // nqs lidhja qe beme me serverin e api ishte ok
              if (status == 200){

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

                      //emri
                      city.setName(name);

                      //vizibiliteti
                      city.setVisibility(Double.parseDouble(myResponse.get("visibility").toString()));

                      JSONObject wind = (JSONObject) myResponse.get("wind");
                      String windSped = wind.get("speed").toString();

                      // shpejtesia e eres
                      city.setWindSpeed(Double.parseDouble(windSped));

                      JSONObject myObject = (JSONObject) myResponse.get("main");

                      Object temp = myObject.get("temp");
                      // temperatura
                      city.setTemperature(Double.parseDouble(temp.toString()));
//

                      city.setDate(DateService.getTodayDate());
//                      Object temp_min = myObject.get("temp_min");
//                      double min_temp =  Double.parseDouble(temp_min.toString());
//
//                      Object temp_max = myObject.get("temp_max");
//                      double max_temp =  Double.parseDouble(temp_min.toString());

                      Object humidity1 = myObject.get("humidity");
                      city.setHumidity(Double.parseDouble(humidity1.toString()));

                  }
                  bufferedReader.close();
              }else {
                  city=null;
              }
        return city;
    }

}
