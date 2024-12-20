package com.quizmanager.service;

import com.quizmanager.dto.academyDto.AcademyRequest;
import com.quizmanager.dto.academyDto.AcademyResponse;
import com.quizmanager.dto.academyDto.AcademyUpdateRequest;
import com.quizmanager.filter.FilterCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AcademyService {
    AcademyResponse createAcademy(AcademyRequest academy);

    AcademyResponse getAcademyById(Long id);
    Page<AcademyResponse> getAllAcademies(Pageable pageable);

    AcademyResponse updateAcademy(AcademyUpdateRequest academy);

    AcademyResponse deleteAcademy(Long id);

    Page<AcademyResponse> searchAcademy(String searchTerm, Pageable pageable);

    Page<AcademyResponse> filterAcademy(List<FilterCriteria<?>> filters, Pageable pageable);
}
