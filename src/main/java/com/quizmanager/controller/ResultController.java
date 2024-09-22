package com.quizmanager.controller;

import com.quizmanager.dto.ResultRequest;
import com.quizmanager.dto.ResultResponse;
import com.quizmanager.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/results")
public class ResultController {
    private final ResultService resultService;

    @PostMapping("/create")
    public ResultResponse create(@RequestBody ResultRequest request) {
        return resultService.create(request);
    }

    @GetMapping("/exam/{examId}")
    public Page<ResultResponse> getResultsByExamId(@PathVariable Long examId,
                                                   @RequestParam(required = false) Pageable pageable) {
        return resultService.getResultsByExamId(examId, pageable);
    }

    @GetMapping("/student/{userId}")
    public List<ResultResponse> getResultsByStudentId(@PathVariable Long userId) {
        return resultService.getResultsByStudentId(userId);
    }

    @GetMapping("/{resultId}/email")
    public String sendResultsAsEmail(@PathVariable Long resultId) {
        return resultService.sendResultsAsEmail(resultId);
    }
}
