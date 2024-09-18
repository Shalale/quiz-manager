package com.quizmanager.repository;

import com.quizmanager.entity.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    Page<Result> findAllByExamId(Long examId, Pageable pageable);
    List<Result> findAllByStudentId(Long studentId);
}
