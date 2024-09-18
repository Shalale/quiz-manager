package com.quizmanager;

import com.quizmanager.dto.QuestionRequest;
import com.quizmanager.dto.QuestionResponse;
import com.quizmanager.dto.QuestionUpdateRequest;
import com.quizmanager.entity.Question;
import com.quizmanager.repository.QuestionRepository;
import com.quizmanager.service.impl.QuestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class QuestionServiceTest {
    @InjectMocks
    private QuestionServiceImpl questionService;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetQuestionById_Success() {
        Long questionId = 1L;
        Question question = new Question();
        question.setId(questionId);
        QuestionResponse expectedResponse = new QuestionResponse();

        when(questionRepository.findById(questionId)).thenReturn(Optional.of(question));
        when(modelMapper.map(question, QuestionResponse.class)).thenReturn(expectedResponse);

        QuestionResponse actualResponse = questionService.getQuestionById(questionId);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(questionRepository, times(1)).findById(questionId);
        verify(modelMapper, times(1)).map(question, QuestionResponse.class);
    }

    @Test
    void testGetQuestionById_NotFound() {
        Long questionId = 1L;

        when(questionRepository.findById(questionId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                questionService.getQuestionById(questionId)
        );

        assertEquals("Question not found with id: " + questionId, exception.getMessage());
        verify(questionRepository, times(1)).findById(questionId);
    }

    @Test
    void testCreateQuestion_Success() {
        QuestionRequest request = new QuestionRequest();
        Question question = new Question();
        Question savedQuestion = new Question();
        QuestionResponse expectedResponse = new QuestionResponse();

        when(modelMapper.map(request, Question.class)).thenReturn(question);
        when(questionRepository.save(question)).thenReturn(savedQuestion);
        when(modelMapper.map(savedQuestion, QuestionResponse.class)).thenReturn(expectedResponse);

        QuestionResponse actualResponse = questionService.create(request);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(modelMapper, times(1)).map(request, Question.class);
        verify(questionRepository, times(1)).save(question);
        verify(modelMapper, times(1)).map(savedQuestion, QuestionResponse.class);
    }

    @Test
    void testUpdateQuestion_Success() {
        QuestionUpdateRequest updateRequest = new QuestionUpdateRequest();
        updateRequest.setId(1L);
        Question question = new Question();
        Question savedQuestion = new Question();
        QuestionResponse expectedResponse = new QuestionResponse();

        when(questionRepository.findById(updateRequest.getId())).thenReturn(Optional.of(question));
        when(questionRepository.save(question)).thenReturn(savedQuestion);
        when(modelMapper.map(savedQuestion, QuestionResponse.class)).thenReturn(expectedResponse);

        QuestionResponse actualResponse = questionService.update(updateRequest);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(questionRepository, times(1)).findById(updateRequest.getId());
        verify(questionRepository, times(1)).save(question);
        verify(modelMapper, times(1)).map(savedQuestion, QuestionResponse.class);
    }

    @Test
    void testUpdateQuestion_NotFound() {
        QuestionUpdateRequest updateRequest = new QuestionUpdateRequest();
        updateRequest.setId(1L);

        when(questionRepository.findById(updateRequest.getId())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                questionService.update(updateRequest)
        );

        assertEquals("Question not found with id: " + updateRequest.getId(), exception.getMessage());
        verify(questionRepository, times(1)).findById(updateRequest.getId());
        verify(questionRepository, never()).save(any(Question.class));
    }

    @Test
    void testDeleteQuestion_Success() {
        Long questionId = 1L;

        when(questionRepository.findById(questionId)).thenReturn(Optional.of(new Question()));

        questionService.delete(questionId);

        verify(questionRepository, times(1)).findById(questionId);
        verify(questionRepository, times(1)).deleteById(questionId);
    }

    @Test
    void testDeleteQuestion_NotFound() {
        Long questionId = 1L;

        when(questionRepository.findById(questionId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                questionService.delete(questionId)
        );

        assertEquals("Question not found with id: " + questionId, exception.getMessage());
        verify(questionRepository, times(1)).findById(questionId);
        verify(questionRepository, never()).deleteById(questionId);
    }

    @Test
    void testGetAllByExamId() {
        Long examId = 1L;
        List<Question> questions = List.of(new Question(), new Question());
        List<QuestionResponse> responses = List.of(new QuestionResponse(), new QuestionResponse());

        when(questionRepository.getAllByExamId(examId)).thenReturn(questions);
        when(modelMapper.map(any(Question.class), eq(QuestionResponse.class))).thenReturn(responses.get(0), responses.get(1));

        List<QuestionResponse> actualResponses = questionService.getAllByExamId(examId);

        assertNotNull(actualResponses);
        assertEquals(responses.size(), actualResponses.size());
        verify(questionRepository, times(1)).getAllByExamId(examId);
        verify(modelMapper, times(questions.size())).map(any(Question.class), eq(QuestionResponse.class));
    }
}
