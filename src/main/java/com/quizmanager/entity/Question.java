package com.quizmanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
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


    @ManyToOne
    @JoinColumn(name = "exam_id")
    Exam exam;

    String type;  // "multiple-choice", "open-ended", "picture"
    String content;
    String pictureUrl;  // For picture-based questions

    @ElementCollection
    Set<String> options;  // Multiple-choice options

    @Column(nullable = false)
    String correctAnswer;
}
