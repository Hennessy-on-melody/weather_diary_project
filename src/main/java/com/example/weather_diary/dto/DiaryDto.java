package com.example.weather_diary.dto;


import com.example.weather_diary.domain.Diary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DiaryDto {
    private String weather;
    private String icon;
    private String text;
    private double temperature;
    private LocalDate date;

    public static DiaryDto from(Diary diary){
        return DiaryDto.builder()
                .weather(diary.getWeather())
                .icon(diary.getIcon())
                .temperature(diary.getTemperature())
                .text(diary.getText())
                .date(diary.getDate())
                .build();
    }
}
