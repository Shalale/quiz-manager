package com.quizmanager.service.impl;

import com.quizmanager.dto.academyDto.AcademyRequest;
import com.quizmanager.dto.academyDto.AcademyResponse;
import com.quizmanager.dto.academyDto.AcademyUpdateRequest;
import com.quizmanager.entity.Academy;
import com.quizmanager.filter.FilterCriteria;
import com.quizmanager.filter.GenericSpecification;
import com.quizmanager.repository.AcademyRepository;
import com.quizmanager.service.AcademyService;
import com.quizmanager.utill.RepositoryUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademyServiceImpl implements AcademyService {
    private final AcademyRepository academyRepository;
    private final ModelMapper mapper;

    @Override
    public AcademyResponse createAcademy(AcademyRequest academy) {
        return null;
    }

    @Override
    public AcademyResponse getAcademyById(Long id) {
        Academy academy = RepositoryUtil.fetchById(academyRepository, id, "Academy");
        return mapper.map(academy, AcademyResponse.class);
    }

    @Override
    public Page<AcademyResponse> getAllAcademies(Pageable pageable) {
        Page<Academy> academyPage = academyRepository.findAll(pageable);
        return academyPage.map(academy -> mapper.map(academy, AcademyResponse.class));
    }

    @Override
    public AcademyResponse updateAcademy(AcademyUpdateRequest academy) {
        return null;
    }

    @Override
    public AcademyResponse deleteAcademy(Long id) {
        return null;
    }

    @Override
    public Page<AcademyResponse> searchAcademy(String searchTerm, Pageable pageable) {
        return null;
    }

    @Override
    public Page<AcademyResponse> filterAcademy(List<FilterCriteria<?>> filters, Pageable pageable) {
        GenericSpecification<Academy> specification = new GenericSpecification<>();
        filters.forEach(specification::add);

        Page<Academy> resultList = academyRepository.findAll(specification, pageable);

        return resultList.map(academy -> mapper.map(academy, AcademyResponse.class));
    }
}
