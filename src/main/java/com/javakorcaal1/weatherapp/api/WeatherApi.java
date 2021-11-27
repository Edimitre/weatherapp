package com.javakorcaal1.weatherapp.api;


import com.javakorcaal1.weatherapp.model.City;
import com.javakorcaal1.weatherapp.model.FutureDay;
import com.javakorcaal1.weatherapp.service.DateService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class WeatherApi {

    private final  static String API_KEY = "44ca06d59a61e9757947ced106f9c59b";

    private static HttpURLConnection connection;

    public static List<FutureDay> futureDayList = new ArrayList<>();

    public static City city;

    public static void main(String[] args) throws IOException {
        getCityByApi("korce");
    }

    public static City getCityByApi(String name) throws IOException {

        int status = 0;
        String byCityName = "https://api.openweathermap.org/data/2.5/weather?q="+name+"&units=metric&lang=al&appid="+API_KEY+"";

        URL url = new URL(byCityName);

        connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        status = connection.getResponseCode();

        connection.setConnectTimeout(5000);



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

              city = findCityInTheServers(name, myResponse);

          }
          bufferedReader.close();
        }else {
          city=null;
        }
        return city;
    }

    public static City findCityInTheServers(String name ,JSONObject jsonObject) throws IOException {


        City city = new City();
        //emri
        city.setName(name);
        //Shteti
        city.setCountry(jsonObject.getJSONObject("sys").getString("country"));

        //vizibiliteti
        city.setVisibility(jsonObject.getDouble("visibility"));

        // shpejtesia e eres
        city.setWindSpeed(jsonObject.getJSONObject("wind").getDouble("speed"));

        // temperatura
        city.setTemperature(jsonObject.getJSONObject("main").getDouble("temp"));

        //data
        city.setDate(DateService.getTodayDate());

        // ora
        city.setRefreshTime(DateService.getCurrentHour());

        // lagestia
        city.setHumidity(jsonObject.getJSONObject("main").getDouble("humidity"));


        // altitude longtitude
        city.setLatitude(jsonObject.getJSONObject("coord").getDouble("lon"));
        city.setLongtitude(jsonObject.getJSONObject("coord").getDouble("lon"));

        city.setCountrycode(Integer.parseInt(jsonObject.get("cod").toString()));


        city.setFutureDays(fillFutureDayListByCityName(city.getName()));

        return city;

    }


    public static List<FutureDay> fillFutureDayListByCityName(String cityName) throws IOException {

        String byName = "https://api.openweathermap.org/data/2.5/forecast?q="+cityName+"&units=metric&appid="+API_KEY+"";


        int status = 0;
        URL url = new URL(byName);

        connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        status = connection.getResponseCode();

        connection.setConnectTimeout(5000);



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



                JSONArray dataList = myResponse.getJSONArray("list");

                FutureDay futureDay1 = getFutureDayByListIndex(dataList,0);
                FutureDay futureDay2 = getFutureDayByListIndex(dataList, 1);
                FutureDay futureDay3 = getFutureDayByListIndex(dataList, 2);
                FutureDay futureDay4 = getFutureDayByListIndex(dataList, 3);
                FutureDay futureDay5 = getFutureDayByListIndex(dataList, 4);

                futureDayList.clear();
                futureDayList.add(futureDay1);
                futureDayList.add(futureDay2);
                futureDayList.add(futureDay3);
                futureDayList.add(futureDay4);
                futureDayList.add(futureDay5);


            }
            bufferedReader.close();
        }
        return futureDayList;
    }

    public static FutureDay getFutureDayByListIndex(JSONArray dataList, int index){

        FutureDay futureDay = new FutureDay();

        //date
        futureDay.setDate(dataList.getJSONObject(index).getString("dt_txt"));
        //temperature
        futureDay.setTemperature(dataList.getJSONObject(index).getJSONObject("main").getDouble("temp"));
        //feels like
        futureDay.setFeelsLike(dataList.getJSONObject(index).getJSONObject("main").getDouble("feels_like"));
        //humidity
        futureDay.setHumidity(dataList.getJSONObject(index).getJSONObject("main").getDouble("humidity"));
        //windSpeed
        futureDay.setWindSpeed(dataList.getJSONObject(index).getJSONObject("wind").getDouble("speed"));

        return futureDay;


    }

}

