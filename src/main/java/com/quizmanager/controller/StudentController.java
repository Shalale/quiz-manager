package com.quizmanager.controller;

import com.quizmanager.dto.StudentRequest;
import com.quizmanager.dto.StudentResponse;
import com.quizmanager.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/create")
    public StudentResponse create(@RequestBody StudentRequest request) {
        return studentService.create(request);
    }

    @PostMapping("/{studentId}")
    public StudentResponse getStudentById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping("/exam/{examId}")
    public List<StudentResponse> getAllStudentsByExamId(@PathVariable Long examId) {
        return studentService.getAllStudentsByExamId(examId);
    }
}
