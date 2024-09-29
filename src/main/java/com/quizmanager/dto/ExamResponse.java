package com.quizmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExamResponse {
    Long id;
    String title;
    String instructions;
    Integer duration;
    Long courseId;
//    Set<QuestionResponse> questions;
    List<StudentResponse> students;
}
