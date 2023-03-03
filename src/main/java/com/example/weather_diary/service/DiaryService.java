package com.example.weather_diary.service;


import com.example.weather_diary.domain.Diary;
import com.example.weather_diary.domain.Weather;
import com.example.weather_diary.dto.DiaryDto;
import com.example.weather_diary.exception.ErrorCode;
import com.example.weather_diary.exception.WeatherDiaryException;
import com.example.weather_diary.repository.DiaryRepository;
import com.example.weather_diary.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final WeatherRepository weatherRepository;

    @Transactional
    public void createDiary(LocalDate date, String text) {
        Weather weather = weatherRepository.findByDate(date)
                .orElseThrow(() -> new WeatherDiaryException(ErrorCode.NOT_FOUND_DATA));

        diaryRepository.save(Diary.builder()
                .weather(weather.getWeather())
                .icon(weather.getIcon())
                .temperature(weather.getTemperature())
                .date(date)
                .text(text)
                .build());
    }

    @Transactional(readOnly = true)
    public List<DiaryDto> getDiaryList(LocalDate date){
        return diaryRepository.findAllByDate(date)
                .stream().map(DiaryDto::from).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DiaryDto> getDiary(LocalDate start, LocalDate end){
        return diaryRepository.findAllByDateBetween(start, end)
                .stream()
                .map(DiaryDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateDiary(LocalDate date, String text){
        Diary diary = diaryRepository.findFirstByDate(date)
                .orElseThrow(() -> new WeatherDiaryException(ErrorCode.NOT_FOUNT_DIARY));

        diary.setText(text);
    }


    @Transactional
    public void deleteDiary(LocalDate date){
        diaryRepository.deleteAllByDate(date);
    }
}
