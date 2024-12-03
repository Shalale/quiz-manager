package com.quizmanager.controller;

import com.quizmanager.dto.QuestionRequest;
import com.quizmanager.dto.QuestionResponse;
import com.quizmanager.dto.QuestionUpdateRequest;
import com.quizmanager.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping("/create")
    public QuestionResponse create(@RequestBody QuestionRequest questionRequest) {
        return questionService.create(questionRequest);
    }

    @GetMapping("/{questionId}")
    public QuestionResponse getQuestionById(@PathVariable Long questionId) {
        return questionService.getQuestionById(questionId);
    }

    @GetMapping("/exam/{examId}")
    public List<QuestionResponse> getAllByExamId(@PathVariable Long examId) {
        return questionService.getAllByExamId(examId);
    }

    @PutMapping("/update")
    public QuestionResponse update(@RequestBody QuestionUpdateRequest questionRequest) {
        return questionService.update(questionRequest);
    }

    @DeleteMapping("/{questionId}")
    public void delete(@PathVariable Long questionId) {
        questionService.delete(questionId);
    }

}
