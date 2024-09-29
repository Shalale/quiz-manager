package com.quizmanager.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResultRequest {
    Long studentId;
    Long examId;
    Integer duration;
    Map<Long, String> studentAnswers; // Question ID -> Student's answer
    String additionalNotes;
}
