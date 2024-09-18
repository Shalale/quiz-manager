package com.quizmanager.service;

import com.quizmanager.dto.ResultRequest;
import com.quizmanager.dto.ResultResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResultService {
    ResultResponse create(ResultRequest request);
    Page<ResultResponse> getResultsByExamId(Long examId, Pageable pageable);

    List<ResultResponse> getResultsByStudentId(Long userId);
    void sendResultsAsEmail(Long resultId);
}
