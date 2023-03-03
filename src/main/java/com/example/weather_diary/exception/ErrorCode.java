package com.example.weather_diary.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND_DATA("정보가 존재하지 않습니다."),
    NOT_FOUNT_DIARY("해당일자의 기록이 없습니다."),
    INTERNAL_SERVER_ERROR("서버 오류가 발생했습니다."),
    INVALID_DATE("유효하지 않은 날짜입니다.");

    private final String msg;
}
