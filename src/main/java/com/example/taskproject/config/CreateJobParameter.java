package com.example.taskproject.config;

import com.example.taskproject.entity.Status;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public class CreateJobParameter {
    private final Status status;
    private final LocalDate createDate;
    private final String userId;

    public CreateJobParameter(String createDateStr, Status status, String userId) {
        this.createDate = LocalDate.parse(createDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.status = status;
        this.userId = userId;
    }
}