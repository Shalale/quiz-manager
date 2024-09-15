package com.quizmanager.service;

import com.quizmanager.dto.CourseRequest;
import com.quizmanager.dto.CourseResponse;

public interface CourseService {
    CourseResponse addCourse(CourseRequest course);

    CourseResponse updateCourse(CourseRequest course);

    void deleteCourse(Long courseId);

    CourseResponse getCourseById(Long courseId);

    CourseResponse getAllCourses();
}
