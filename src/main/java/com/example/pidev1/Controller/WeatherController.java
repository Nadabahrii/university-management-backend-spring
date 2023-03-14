package com.example.pidev1.Controller;

import com.example.pidev1.Entity.WeatherResponse;
import com.example.pidev1.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;


@Controller
public class WeatherController {

    private static final String API_KEY = "c924c08f1028300c327ecdaad43ed9f9";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=";

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather/{city}")
    @ResponseBody
    public String getWeatherData(@PathVariable String city) {
        String weatherData = fetchWeatherData(city);
        JSONObject json = new JSONObject(weatherData);
        JSONObject main = json.getJSONObject("main");
        double temperature = main.getDouble("temp");
        int humidity = main.getInt("humidity");
        String description = json.getJSONArray("weather").getJSONObject(0).getString("description");
        JSONObject result = new JSONObject();
        result.put("temperature", temperature);
        result.put("humidity", humidity);
        result.put("description", description);
        return result.toString();
    }

    private String fetchWeatherData(String city) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(API_URL + city + "&appid=" + API_KEY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    @GetMapping("/weather/{city}/{date}")
    public ResponseEntity<String> getWeather(@PathVariable String city, @PathVariable String date) {

        // Convert date to Unix timestamp
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = null;
        try {
            parsedDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long unixTimestamp = parsedDate.getTime() / 1000L;

        // Make API call
        String url = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&dt=%d&appid=%s&units=metric", city, unixTimestamp, API_KEY);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return response;
    }

      /*@GetMapping("/{city}")
    public WeatherResponse getWeather(@PathVariable("city") String city,
                                      @RequestParam("date") String dateString) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(dateString);
        Instant instant = date.toInstant();
        long timestamp = instant.getEpochSecond();

        WeatherResponse weatherResponse = weatherService.getWeather(city, timestamp);
        return weatherResponse;
    }*/
}
