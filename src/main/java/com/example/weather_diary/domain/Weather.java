package com.example.weather_diary.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Weather {
    @Id
    private LocalDateTime date;
    private String weather;
    private String icon;
    private double temperature;
}
