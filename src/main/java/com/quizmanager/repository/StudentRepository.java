package com.quizmanager.repository;

import com.quizmanager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByExamsId(Long id);
    Optional<Student> findByEmail(String email);
}
