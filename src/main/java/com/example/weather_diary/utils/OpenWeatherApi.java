package com.example.weather_diary.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
public class OpenWeatherApi {
    @Value("1ac079210d14995f9a359084a2ed2b93")
    private String key;

    @Value("https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=")
    private String apiUrl;

    public Map<String, Object> getNowWeather(){
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;

        try{
            String stringWeather = getStringWeather();
            System.out.println(stringWeather);
            jsonObject = (JSONObject) jsonParser.parse(stringWeather);
        } catch (ParseException e){
            throw new RuntimeException(e);
        }
        Map<String, Object> resultMap = new HashMap<>();

        JSONArray jsonArray = (JSONArray) jsonObject.get("weather");
        JSONObject weatherData = (JSONObject) jsonArray.get(0);

        JSONObject mainData = (JSONObject) jsonObject.get("main");
        resultMap.put("temp", mainData.get("temp"));
        resultMap.put("main", weatherData.get("main"));
        resultMap.put("icon", weatherData.get("icon"));
        return resultMap;
    }

    private String getStringWeather(){
        try {
            URL url = new URL(apiUrl + key);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() != 200) {
                throw new RuntimeException(readApiStream((connection.getErrorStream())));
            }

            return readApiStream(connection.getInputStream());
        } catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private String readApiStream(InputStream inputStream)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String inputLine;

        StringBuilder response = new StringBuilder();

        while((inputLine = br.readLine()) != null){
            response.append(inputLine);
        }
        br.close();
        return response.toString();
    }
}
