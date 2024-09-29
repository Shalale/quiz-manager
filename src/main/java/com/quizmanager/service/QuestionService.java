package com.quizmanager.service;

import com.quizmanager.dto.QuestionRequest;
import com.quizmanager.dto.QuestionResponse;
import com.quizmanager.dto.QuestionUpdateRequest;

public interface QuestionService {
    QuestionResponse getQuestionById(Long questionId);
    List<QuestionResponse> getAllByExamId(Long examId);
    QuestionResponse create(QuestionRequest questionRequest);
    QuestionResponse update(QuestionUpdateRequest questionRequest);
    void delete(Long questionId);
}
