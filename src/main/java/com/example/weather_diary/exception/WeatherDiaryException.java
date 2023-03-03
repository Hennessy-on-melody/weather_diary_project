package com.example.weather_diary.exception;


import lombok.Getter;

@Getter
public class WeatherDiaryException extends RuntimeException{
    private final ErrorCode errorCode;
    private final String errorMsg;
    public WeatherDiaryException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMsg = errorCode.getMsg();
    }
}
