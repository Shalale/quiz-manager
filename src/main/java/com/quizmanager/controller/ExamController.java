package com.quizmanager.controller;

import com.quizmanager.dto.ExamRequest;
import com.quizmanager.dto.ExamResponse;
import com.quizmanager.dto.ExamUpdateRequest;
import com.quizmanager.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/course/{id}")
    public Page<ExamResponse> getAllByCourse(@PathVariable Long id, @RequestParam(required = false) Pageable pageable) {
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
}
