package com.quizmanager.dto;

import lombok.Data;

@Data
public class QuestionUpdateRequest extends QuestionRequest {
    private Long id;
}
