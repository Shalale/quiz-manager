package com.quizmanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    Exam exam;

    // Total score achieved by the student
    @Column(nullable = false)
    Double score;

    // Percentage of correct answers
    @Column(nullable = false)
    Double percentage;

    // Whether the student passed or failed
    @Column(nullable = false)
    Boolean passed;

    // Total correct and incorrect answers
    @Column(nullable = false)
    Integer correctAnswers;

    @Column(nullable = false)
    Integer incorrectAnswers;

    // Timestamp when the result was submitted
    @Column(nullable = false)
    LocalDateTime submittedAt;

    // Duration taken to complete the exam (in minutes)
    @Column(nullable = false)
    Integer duration;

    @ElementCollection
    Map<Long, String> studentAnswers; // Question ID -> Student's answer

    String additionalNotes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(id, result.id) && Objects.equals(student, result.student) && Objects.equals(score, result.score) && Objects.equals(percentage, result.percentage) && Objects.equals(passed, result.passed) && Objects.equals(correctAnswers, result.correctAnswers) && Objects.equals(incorrectAnswers, result.incorrectAnswers) && Objects.equals(submittedAt, result.submittedAt) && Objects.equals(duration, result.duration) && Objects.equals(additionalNotes, result.additionalNotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, score, percentage, passed, correctAnswers, incorrectAnswers, submittedAt, duration, additionalNotes);
    }
}
