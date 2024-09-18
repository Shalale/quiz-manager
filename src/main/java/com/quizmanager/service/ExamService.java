package com.quizmanager.service;

import com.quizmanager.dto.ExamRequest;
import com.quizmanager.dto.ExamResponse;
import com.quizmanager.dto.ExamUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExamService {
    ExamResponse getExamById(Long id);

    Page<ExamResponse> getAllByCourse(Long id, Pageable pageable);
    ExamResponse create(ExamRequest request);

    ExamResponse update(ExamUpdateRequest request);

    void delete(Long id);
}
