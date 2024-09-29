package com.quizmanager.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id", nullable = false)
    Exam exam;

    String type;  // "multiple-choice", "open-ended", "picture"
    String questionText;
    String pictureUrl;  // For picture-based questions

    @ElementCollection(fetch = FetchType.EAGER)
    Set<String> options;  // Multiple-choice options

    @Column(nullable = false)
    String correctAnswer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) && Objects.equals(type, question.type) && Objects.equals(questionText, question.questionText) && Objects.equals(pictureUrl, question.pictureUrl) && Objects.equals(options, question.options) && Objects.equals(correctAnswer, question.correctAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, questionText, pictureUrl, options, correctAnswer);
    }
}
