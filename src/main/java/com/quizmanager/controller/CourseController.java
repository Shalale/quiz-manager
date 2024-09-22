package com.quizmanager.controller;

import com.quizmanager.dto.CourseRequest;
import com.quizmanager.dto.CourseResponse;
import com.quizmanager.dto.CourseUpdateRequest;
import com.quizmanager.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/create")
    public CourseResponse create(@RequestBody CourseRequest course) {
        return courseService.create(course);
    }

    @PutMapping("/update")
    public CourseResponse update(@RequestBody CourseUpdateRequest course) {
        return courseService.update(course);
    }

    @GetMapping("/{courseId}")
    public CourseResponse getCourseById(@PathVariable Long courseId) {
        return courseService.getCourseById(courseId);
    }

    @GetMapping()
    public Page<CourseResponse> getAllCourses(@RequestParam(required = false) String search, Pageable pageable) {
        return courseService.getAllCourses(pageable);
    }

    @DeleteMapping("/{courseId}")
    public void delete(@PathVariable Long courseId) {
        courseService.delete(courseId);
    }
}
