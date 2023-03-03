package com.example.weather_diary.service;

import com.example.weather_diary.WeatherDiaryApplication;
import com.example.weather_diary.domain.Weather;
import com.example.weather_diary.repository.WeatherRepository;
import com.example.weather_diary.utils.OpenWeatherApi;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class WeatherService {
    private final WeatherRepository weatherRepository;
    private final OpenWeatherApi openWeatherApi;
    private static final Logger logger = LoggerFactory.getLogger(WeatherDiaryApplication.class);


    @Transactional
    @Scheduled(cron = "0 0 1 * * *")
    public void saveWeatherData() {
        Map<String, Object> weatherMap = openWeatherApi.getNowWeather();

        weatherRepository.save(Weather.builder()
                .date(LocalDateTime.from(LocalDate.now()))
                .weather(weatherMap.get("main").toString())
                .icon(weatherMap.get("icon").toString())
                .temperature((Double) weatherMap.get("map"))
                .build());

        logger.debug("open weather api data saved");
    }
}
