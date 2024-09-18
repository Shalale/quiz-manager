package com.quizmanager.service;

import com.quizmanager.dto.StudentRequest;
import com.quizmanager.dto.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse create(StudentRequest request);

    StudentResponse getStudentById(Long studentId);

    List<StudentResponse> getAllStudentsByExamId(Long examId);
}
