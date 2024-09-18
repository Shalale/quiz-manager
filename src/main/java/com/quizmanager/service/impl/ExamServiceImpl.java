package com.quizmanager.service.impl;

import com.quizmanager.dto.ExamRequest;
import com.quizmanager.dto.ExamResponse;
import com.quizmanager.dto.ExamUpdateRequest;
import com.quizmanager.entity.Exam;
import com.quizmanager.repository.ExamRepository;
import com.quizmanager.service.ExamService;
import com.quizmanager.utill.RepositoryUtil;
import com.quizmanager.utill.UpdateHelper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;
    private final ModelMapper mapper;

    @Override
    public ExamResponse create(ExamRequest request) {
        Exam exam = mapper.map(request, Exam.class);
        Exam savedExam = examRepository.save(exam);
        return mapper.map(savedExam, ExamResponse.class);
        }

    @Override
    public ExamResponse getExamById(Long id) {
        Exam exam = RepositoryUtil.fetchById(examRepository, id);
        return mapper.map(exam, ExamResponse.class);
    }

    @Override
    public Page<ExamResponse> getAllByCourse(Long id, Pageable pageable) {
        Page<Exam> examPage = examRepository.findAllByCourseId(id, pageable);
        List<ExamResponse> examResponses = examPage.stream()
                .map(
                        exam -> mapper.map(exam, ExamResponse.class))
                .toList();
        return new PageImpl<>(examResponses, pageable, examPage.getTotalElements());
    }

    @Override
    public ExamResponse update(ExamUpdateRequest request) {
        Exam exam = RepositoryUtil.fetchById(examRepository, request.getId());
        BeanUtils.copyProperties(request,exam, UpdateHelper.getNullPropertyNames(exam));
        Exam savedExam = examRepository.save(exam);
        return mapper.map(savedExam, ExamResponse.class);
    }

    @Override
    public void delete(Long id) {
        RepositoryUtil.fetchById(examRepository, id);
        examRepository.deleteById(id);
    }
}
