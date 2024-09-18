package com.quizmanager.repository;

import com.quizmanager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByExam_Id(Long id);
}
