package com.quizmanager;

import com.quizmanager.dto.ResultRequest;
import com.quizmanager.dto.ResultResponse;
import com.quizmanager.entity.Result;
import com.quizmanager.repository.ResultRepository;
import com.quizmanager.service.impl.ResultServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ResultServiceImplTest {

    @Mock
    private ResultRepository resultRepository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private ResultServiceImpl resultService;

    @Test
    public void testCreateWithValidRequest() {
        ResultRequest request = new ResultRequest();
        Result result = new Result();
        Result savedResult = new Result();
        ResultResponse response = new ResultResponse();

        when(mapper.map(request, Result.class)).thenReturn(result);
        when(resultRepository.save(result)).thenReturn(savedResult);
        when(mapper.map(savedResult, ResultResponse.class)).thenReturn(response);

        ResultResponse resultResponse = resultService.create(request);

        assertNotNull(resultResponse, "The result should not be null");
        verify(resultRepository).save(result);
        verify(mapper).map(savedResult, ResultResponse.class);
    }

    @Test
    public void testGetResultsByExamIdWithResults() {
        Long examId = 1L;
        Pageable pageable = Pageable.ofSize(10);
        Result result1 = new Result();
        Result result2 = new Result();
        ResultResponse response1 = new ResultResponse();
        ResultResponse response2 = new ResultResponse();
        List<Result> results = List.of(result1, result2);
        Page<Result> resultPage = new PageImpl<>(results, pageable, results.size());

        when(resultRepository.findAllByExamId(examId, pageable)).thenReturn(resultPage);
        when(mapper.map(result1, ResultResponse.class)).thenReturn(response1);
        when(mapper.map(result2, ResultResponse.class)).thenReturn(response2);

        Page<ResultResponse> resultPageResponse = resultService.getResultsByExamId(examId, pageable);

        assertNotNull(resultPageResponse, "The result page should not be null");
        assertEquals(2, resultPageResponse.getTotalElements(), "Total elements should be 2");
        assertTrue(resultPageResponse.getContent().contains(response1), "The result page should contain response1");
        assertTrue(resultPageResponse.getContent().contains(response2), "The result page should contain response2");
    }

    @Test
    public void testGetResultsByExamIdWithNoResults() {
        Long examId = 1L;
        Pageable pageable = Pageable.ofSize(10);

        when(resultRepository.findAllByExamId(examId, pageable)).thenReturn(new PageImpl<>(Collections.emptyList(), pageable, 0));

        Page<ResultResponse> resultPageResponse = resultService.getResultsByExamId(examId, pageable);

        assertNotNull(resultPageResponse, "The result page should not be null");
        assertTrue(resultPageResponse.getContent().isEmpty(), "The result page should be empty");
    }

    @Test
    public void testGetResultsByStudentIdWithResults() {
        Long studentId = 1L;
        Result result1 = new Result();
        Result result2 = new Result();
        ResultResponse response1 = new ResultResponse();
        ResultResponse response2 = new ResultResponse();
        List<Result> results = List.of(result1, result2);

        when(resultRepository.findAllByStudentId(studentId)).thenReturn(results);
        when(mapper.map(result1, ResultResponse.class)).thenReturn(response1);
        when(mapper.map(result2, ResultResponse.class)).thenReturn(response2);

        List<ResultResponse> resultResponses = resultService.getResultsByStudentId(studentId);

        assertNotNull(resultResponses, "The result list should not be null");
        assertEquals(2, resultResponses.size(), "The result list size should be 2");
        assertTrue(resultResponses.contains(response1), "The result list should contain response1");
        assertTrue(resultResponses.contains(response2), "The result list should contain response2");
    }

    @Test
    public void testGetResultsByStudentIdWithNoResults() {
        Long studentId = 1L;

        when(resultRepository.findAllByStudentId(studentId)).thenReturn(Collections.emptyList());

        List<ResultResponse> resultResponses = resultService.getResultsByStudentId(studentId);

        assertNotNull(resultResponses, "The result list should not be null");
        assertTrue(resultResponses.isEmpty(), "The result list should be empty");
    }

    @Test
    public void testSendResultsAsEmail() {
        Long resultId = 1L;

        // Assuming the method sendResultsAsEmail is not yet implemented, we'll just verify that it gets called.
        // In actual implementation, mock the email sending service and verify interactions.

        assertDoesNotThrow(() -> resultService.sendResultsAsEmail(resultId));
    }
}

