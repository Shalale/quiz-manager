package com.quizmanager.service;

import com.quizmanager.dto.StudentRequest;
import com.quizmanager.dto.StudentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    StudentResponse create(StudentRequest request);

    StudentResponse getStudentById(Long studentId);

    Page<StudentResponse> getAllStudents(Pageable pageable);

    List<StudentResponse> getAllStudentsByExamId(Long examId);
}
