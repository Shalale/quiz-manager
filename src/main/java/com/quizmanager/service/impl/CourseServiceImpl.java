package com.quizmanager.service.impl;

import com.quizmanager.dto.CourseRequest;
import com.quizmanager.dto.CourseResponse;
import com.quizmanager.dto.CourseUpdateRequest;
import com.quizmanager.entity.Course;
import com.quizmanager.repository.CourseRepository;
import com.quizmanager.service.CourseService;
import com.quizmanager.utill.RepositoryUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.quizmanager.utill.UpdateHelper.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final ModelMapper mapper;

    @Override
    public CourseResponse create(CourseRequest request) {
        Course course = mapper.map(request, Course.class);
        Course savedCourse = courseRepository.save(course);
        return mapper.map(savedCourse, CourseResponse.class);
    }

    @Override
    public CourseResponse update(CourseUpdateRequest request) {
        Course course = RepositoryUtil.fetchById(courseRepository, request.getId());
        BeanUtils.copyProperties(request, course, getNullPropertyNames(request));
        Course savedCourse = courseRepository.save(course);
        return mapper.map(savedCourse, CourseResponse.class);
    }

    @Override
    public void delete(Long courseId) {
        RepositoryUtil.fetchById(courseRepository, courseId);
        courseRepository.deleteById(courseId);
    }

    @Override
    public CourseResponse getCourseById(Long courseId) {
        Course course = RepositoryUtil.fetchById(courseRepository, courseId);
        return mapper.map(course, CourseResponse.class);
    }

    @Override
    public Page<CourseResponse> getAllCourses(Pageable pageable) {
        Page<Course> courses = courseRepository.findAll(pageable);

        List<CourseResponse> responses = courses.getContent()
                .stream()
                .map(course -> mapper.map(course, CourseResponse.class))
                .toList();
        return new PageImpl<>(responses, pageable, courses.getTotalElements());
    }


}
