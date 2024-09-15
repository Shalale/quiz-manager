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
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String instructor;

    @ManyToOne
    @JoinColumn(name = "academy_id")
    Academy academy;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    Set<Exam> exams;
}
