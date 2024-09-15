package com.quizmanager.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionResponse {
    Long id;
    Long examId;
    String type;
    String questionText;
    String correctAnswer;
    String pictureUrl;
    Set<String> options;
}
