package com.example.pidev1.Service;

import com.example.pidev1.Entity.WeatherResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class WeatherService {

    private static final String API_KEY = "c924c08f1028300c327ecdaad43ed9f9\n";
    private static final String API_URL = "https://api.weatherapi.com/v1/current.json?key=" + API_KEY + "&q=";

    /*public static void main(String[] args) {
        String cityName = "Paris";
        String weatherData = getWeatherData(cityName);
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(weatherData, JsonObject.class);
        JsonObject current = json.getAsJsonObject("current");
        double temperature = current.get("temp_c").getAsDouble();
        int humidity = current.get("humidity").getAsInt();
        String description = current.getAsJsonObject("condition").get("text").getAsString();
        System.out.println("Temperature: " + temperature + " C");
        System.out.println("Humidity: " + humidity + "%");
        System.out.println("Description: " + description);
    }

    private static String getWeatherData(String cityName) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(API_URL + cityName);
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
    }*/

    /*public WeatherResponse getWeather(String city, LocalDate date) {
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY;

        RestTemplate restTemplate = new RestTemplate();
        WeatherResponse response = restTemplate.getForObject(apiUrl, WeatherResponse.class);

        return response;
    }*/
    //@Value("${weather.api.key}")
   /* private String apiKey = "c924c08f1028300c327ecdaad43ed9f9";
    public WeatherResponse

    getWeatherForCityAndDate(String city, String date) {
        // Construct the URL to the OpenWeatherMap API endpoint
        String apiUrl = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric", city, apiKey);

        // Send a GET request to the API endpoint and parse the response JSON
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        JsonObject jsonObject = JsonParser.parseString(response.getBody()).getAsJsonObject();

        // Extract the weather information from the response JSON
        String cityName = jsonObject.get("name").getAsString();
        JsonObject main = jsonObject.getAsJsonObject("main");
        Double temperature = main.get("temp").getAsDouble();
        Integer humidity = main.get("humidity").getAsInt();
        JsonObject wind = jsonObject.getAsJsonObject("wind");
        Double windSpeed = wind.get("speed").getAsDouble();
        JsonArray weatherArray = jsonObject.getAsJsonArray("weather");
        JsonObject weather = weatherArray.get(0).getAsJsonObject();
        String description = weather.get("description").getAsString();
        String iconCode = weather.get("icon").getAsString();

        // Convert the date string to a LocalDateTime object
        LocalDateTime targetDate = LocalDateTime.parse(date + "T00:00:00");

        // Extract the timestamp from the response JSON and convert it to a LocalDateTime object
        Long timestamp = jsonObject.get("dt").getAsLong();
        LocalDateTime responseDate = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC);

        // If the response date is not the target date, return an error response
        if (!responseDate.toLocalDate().isEqual(targetDate.toLocalDate())) {
            return new WeatherResponse("ERROR", String.format("No weather data available for %s on %s", cityName, date));
        }

        // Construct the weather response object and return it
        return new WeatherResponse(cityName, temperature, humidity, windSpeed, description, iconCode);
    }*/



}
