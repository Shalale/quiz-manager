package com.quizmanager.service.impl;

import com.quizmanager.dto.QuestionRequest;
import com.quizmanager.dto.QuestionResponse;
import com.quizmanager.dto.QuestionUpdateRequest;
import com.quizmanager.entity.Exam;
import com.quizmanager.entity.Question;
import com.quizmanager.repository.ExamRepository;
import com.quizmanager.repository.QuestionRepository;
import com.quizmanager.service.QuestionService;
import com.quizmanager.utill.RepositoryUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static com.quizmanager.utill.UpdateHelper.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final ExamRepository examRepository;
    private final ModelMapper mapper;

    @Override
    public QuestionResponse getQuestionById(Long questionId) {
        Question question = RepositoryUtil.fetchById(questionRepository, questionId, "Question");
        return mapper.map(question, QuestionResponse.class);
    }

    @Override
    public List<QuestionResponse> getAllByExamId(Long examId) {
        Exam exam = RepositoryUtil.fetchById(examRepository, examId, "Exam");
        List<Question> questions = questionRepository.getAllByExamId(examId);
        return questions.stream().map(
                question -> mapper.map(question, QuestionResponse.class))
                .toList();
    }

    public QuestionResponse create(QuestionRequest request) {
        Question question = mapper.map(request, Question.class);
        Question savedQuestion = questionRepository.save(question);
        return mapper.map(savedQuestion, QuestionResponse.class);
    }

    public QuestionResponse update(QuestionUpdateRequest request) {
        Question question = RepositoryUtil.fetchById(questionRepository, request.getId(), "Question");
        BeanUtils.copyProperties(request, question, getNullPropertyNames(request));
        Question savedQuestion = questionRepository.save(question);
        return mapper.map(savedQuestion, QuestionResponse.class);
    }

    public void delete(Long questionId) {
        RepositoryUtil.fetchById(questionRepository, questionId, "Question");
        questionRepository.deleteById(questionId);
    }
}
