package com.quizmanager.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExamRequest {
    String title;
    String instructions;
    Integer duration;
    Long courseId;
    List<QuestionRequest> questions;
    List<StudentRequest> students;
}
