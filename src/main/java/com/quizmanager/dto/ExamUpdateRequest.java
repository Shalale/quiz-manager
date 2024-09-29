package com.quizmanager.dto;

import lombok.Data;

@Data
public class ExamUpdateRequest{
    private Long id;
    String title;
    String instructions;
    Integer duration;
    Long courseId;
}
