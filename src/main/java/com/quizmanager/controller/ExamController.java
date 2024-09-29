package com.quizmanager.controller;

import com.quizmanager.dto.ExamRequest;
import com.quizmanager.dto.ExamResponse;
import com.quizmanager.dto.ExamUpdateRequest;
import com.quizmanager.dto.StudentRequest;
import com.quizmanager.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exams")
public class ExamController {
    private final ExamService examService;

    @PostMapping("/create")
    public ExamResponse create(@RequestBody ExamRequest request) {
        return examService.create(request);
    }

    @GetMapping("/{id}")
    public ExamResponse getExamById(@PathVariable Long id) {
        return examService.getExamById(id);
    }

    @GetMapping()//@Todo: Add search functionality
    public Page<ExamResponse> getAll(@PageableDefault(size = 10) Pageable pageable) {
        return examService.getAll(pageable);
    }

    @GetMapping("/course/{id}")
    public Page<ExamResponse> getAllByCourse(@PathVariable Long id, @PageableDefault(size = 10) Pageable pageable) {
        return examService.getAllByCourse(id, pageable);
    }

    @PutMapping("/update")
    public ExamResponse update(@RequestBody ExamUpdateRequest request) {
        return examService.update(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        examService.delete(id);
        }

    @PostMapping("/{examId}/add-students")
    public ExamResponse addStudentsToExam(@PathVariable Long examId,
                                       @RequestBody List<StudentRequest> studentRequestList) {
        return examService.addStudentsToExam(examId, studentRequestList);
    }
}
