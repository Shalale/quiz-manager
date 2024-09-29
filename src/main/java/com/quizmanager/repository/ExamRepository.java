package com.quizmanager.repository;

import com.quizmanager.entity.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    Page<Exam> findAllByCourseId(Long id, Pageable pageable);
}
