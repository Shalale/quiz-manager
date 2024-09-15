package com.quizmanager.service;

import com.quizmanager.dto.QuestionRequest;
import com.quizmanager.dto.QuestionResponse;
import com.quizmanager.dto.QuestionUpdateRequest;

import java.util.List;

public interface QuestionService {
    QuestionResponse getQuestionById(Long questionId);
    QuestionResponse createQuestion(QuestionRequest questionRequest);
    QuestionResponse updateQuestion(QuestionUpdateRequest questionRequest);
    void deleteQuestion(Long questionId);
    List<QuestionResponse> getAllByExamId(Long examId);
}
