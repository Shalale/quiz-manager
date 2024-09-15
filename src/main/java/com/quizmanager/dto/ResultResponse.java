package com.quizmanager.dto;

import com.quizmanager.entity.Exam;
import com.quizmanager.entity.Student;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResultResponse {
    Long id;
    Long studentId;
    Long examId;
    Double score;
    Double percentage;
    Boolean passed;
    Integer correctAnswers;
    Integer incorrectAnswers;
    @CreationTimestamp
    LocalDateTime submittedAt;
    Integer duration;
    Map<Long, String> studentAnswers; // Question ID -> Student's answer
    String additionalNotes;
}
