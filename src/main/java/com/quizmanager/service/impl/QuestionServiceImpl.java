package com.quizmanager.service.impl;

import com.quizmanager.dto.QuestionRequest;
import com.quizmanager.dto.QuestionResponse;
import com.quizmanager.dto.QuestionUpdateRequest;
import com.quizmanager.entity.Question;
import com.quizmanager.repository.QuestionRepository;
import com.quizmanager.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import static com.quizmanager.utill.UpdateHelper.getNullPropertyNames;

@Service
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final ModelMapper mapper;

    @Override
    public QuestionResponse getQuestionById(Long questionId) {
        Question question = fetchById(questionId);
        return mapper.map(question, QuestionResponse.class);
    }

    @Override
    public List<QuestionResponse> getAllByExamId(Long examId) {
        List<Question> questions = questionRepository.getAllByExamId(examId);
        return questions.stream().map(
                question -> mapper.map(question, QuestionResponse.class))
                .toList();
    }

    public QuestionResponse createQuestion(QuestionRequest request) {
        Question question = mapper.map(request, Question.class);
        Question savedQuestion = questionRepository.save(question);
        return mapper.map(savedQuestion, QuestionResponse.class);
    }

    public QuestionResponse updateQuestion(QuestionUpdateRequest request) {
        Question question = fetchById(request.getId());
        BeanUtils.copyProperties(request, question, getNullPropertyNames(request));
        Question savedQuestion = questionRepository.save(question);
        return mapper.map(savedQuestion, QuestionResponse.class);
    }

    public void deleteQuestion(Long questionId) {
        fetchById(questionId);
        questionRepository.deleteById(questionId);
    }


    private Question fetchById(Long questionId){
        return questionRepository.findById(questionId).orElseThrow(
                () -> new RuntimeException("Question not found with id: " + questionId)
        );
    }
}
