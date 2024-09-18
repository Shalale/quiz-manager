package com.quizmanager.service.impl;

import com.quizmanager.dto.ResultRequest;
import com.quizmanager.dto.ResultResponse;
import com.quizmanager.entity.Result;
import com.quizmanager.repository.ResultRepository;
import com.quizmanager.service.ResultService;
import com.quizmanager.utill.RepositoryUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
    private final ModelMapper mapper;

    @Override
    public ResultResponse create(ResultRequest request) {
        Result result = mapper.map(request, Result.class);
        Result savedResult = resultRepository.save(result);
        return mapper.map(savedResult, ResultResponse.class);
    }
    @Override
    public Page<ResultResponse> getResultsByExamId(Long examId, Pageable pageable) {
        Page<Result> results = resultRepository.findAllByExamId(examId, pageable);
        List<ResultResponse> responses = results.stream()
                .map(
                        result -> mapper.map(result, ResultResponse.class))
                .toList();
        return new PageImpl<>(responses, pageable, results.getTotalElements());
    }

    @Override
    public List<ResultResponse> getResultsByStudentId(Long userId) {
        List<Result> results = resultRepository.findAllByStudentId(userId);

        return results.stream()
                .map(result -> mapper.map(result, ResultResponse.class))
                .toList();
    }

    @Override
    public void sendResultsAsEmail(Long resultId) {
        // TODO: Implement email functionality
    }
}
