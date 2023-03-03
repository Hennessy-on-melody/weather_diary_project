package com.example.weather_diary.repository;

import com.example.weather_diary.domain.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, LocalDate> {
    Optional<Weather> findByDate(LocalDate date);
}
