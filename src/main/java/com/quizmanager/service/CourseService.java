package com.quizmanager.service;

import com.quizmanager.dto.CourseRequest;
import com.quizmanager.dto.CourseResponse;
import com.quizmanager.dto.CourseUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {
    CourseResponse create(CourseRequest course);

    CourseResponse update(CourseUpdateRequest course);

    void delete(Long courseId);

    CourseResponse getCourseById(Long courseId);

    Page<CourseResponse> getAllCourses(Pageable pageable);
}
