package com.example.weather_diary.dto;

import com.example.weather_diary.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private final ErrorCode errorCode;
    private final String msg;

    public ErrorResponse(ErrorCode errorCode){
        this.errorCode = errorCode;
        this.msg = errorCode.getMsg();
    }
}
