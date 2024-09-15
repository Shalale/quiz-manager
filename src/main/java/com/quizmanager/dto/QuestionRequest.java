package com.quizmanager.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionRequest {
    String type;
    String questionText;
    String pictureUrl;
    Set<String> options;
    String correctAnswer;
    Long examId;
}
