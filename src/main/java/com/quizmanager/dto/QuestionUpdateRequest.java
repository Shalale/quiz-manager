package com.quizmanager.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionUpdateRequest extends QuestionRequest {
    private Long id;
}
