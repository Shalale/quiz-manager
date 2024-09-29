package com.quizmanager.service;

import com.quizmanager.dto.ExamRequest;
import com.quizmanager.dto.ExamResponse;
import com.quizmanager.dto.ExamUpdateRequest;
import com.quizmanager.dto.StudentRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExamService {
    ExamResponse getExamById(Long id);

    Page<ExamResponse> getAll(Pageable pageable);

    Page<ExamResponse> getAllByCourse(Long id, Pageable pageable);

    ExamResponse create(ExamRequest request);

    ExamResponse update(ExamUpdateRequest request);

    void delete(Long id);

    ExamResponse addStudentsToExam(Long examId, List<StudentRequest> studentRequestList);
}
