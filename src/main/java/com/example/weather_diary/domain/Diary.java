package com.example.weather_diary.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Diary {
    @Id
    @GeneratedValue
    private Long id;
    private String weather;
    private String icon;
    private double temperature;
    private String text;
    private LocalDate date;
}
