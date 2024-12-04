package com.quizmanager.controller;

import com.quizmanager.dto.academyDto.AcademyResponse;
import com.quizmanager.filter.FilterCriteria;
import com.quizmanager.service.AcademyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/academies")
public class AcademyController {
    private final AcademyService academyService;

    @PostMapping("/filter")
    public Page<AcademyResponse> filterAcademy(@RequestBody List<FilterCriteria<?>> filters,
                                               @PageableDefault Pageable pageable){
        return academyService.filterAcademy(filters, pageable);
    }

}
