package com.example.weather_diary.controller;

import com.example.weather_diary.dto.DiaryDto;
import com.example.weather_diary.dto.DiaryResponse;
import com.example.weather_diary.service.DiaryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class DiaryController {
    private final DiaryService diaryService;

    @ApiOperation("Diary 생성")
    @PostMapping("/diary")
    public DiaryResponse createDiary(@RequestParam
                                     @ApiParam(value = "yyyy-MM-dd", example = "2023-03-03")LocalDate date, @RequestBody String text){
        diaryService.createDiary(date, text);
        return new DiaryResponse("다이어리가 생성되었습니다.");
    }

    @ApiOperation("Diary조회")
    @GetMapping("/diary")
    public List<DiaryDto> readDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                    @ApiParam(value = "yyyy-MM-dd", example = "2023-03-03") LocalDate start,
                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                    @ApiParam(value = "yyyy-MM-dd", example = "2023-03-03")LocalDate end){
        return diaryService.getDiary(start, end);
    }

    @ApiOperation("Diary내용변경")
    @PutMapping("diary")
    public DiaryResponse updateDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                     @ApiParam(value = "yyyy-MM-dd", example = "20203-03-03") LocalDate date,
                                     @RequestBody String text){
        diaryService.updateDiary(date, text);

        return new DiaryResponse("다이어리가 수정되었습니다.");
    }

    @ApiOperation("Diary삭제")
    @DeleteMapping("/diary")
    public DiaryResponse deleteDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                     @ApiParam(value = "yyyy-MM-dd", example = "2023-03-03")LocalDate date){
        diaryService.deleteDiary(date);

        return new DiaryResponse("다이어리가 삭제되었습니다.");
    }
}
