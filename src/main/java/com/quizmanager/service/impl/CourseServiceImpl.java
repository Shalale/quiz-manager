package com.quizmanager.service.impl;

import com.quizmanager.dto.CourseRequest;
import com.quizmanager.dto.CourseResponse;
import com.quizmanager.dto.CourseUpdateRequest;
import com.quizmanager.entity.Academy;
import com.quizmanager.entity.Course;
import com.quizmanager.repository.AcademyRepository;
import com.quizmanager.repository.CourseRepository;
import com.quizmanager.service.CourseService;
import com.quizmanager.utill.RepositoryUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.quizmanager.utill.UpdateHelper.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final AcademyRepository academyRepository;
    private final ModelMapper mapper;

    @Override
    public CourseResponse create(CourseRequest request) {
        Course course = mapper.map(request, Course.class);
        course.setId(null);
        Course savedCourse = courseRepository.save(course);
        return mapper.map(savedCourse, CourseResponse.class);
    }

    @Transactional
    public CourseResponse update(CourseUpdateRequest request) {
        Course course = RepositoryUtil.fetchById(courseRepository, request.getId(), "Course");

        if (request.getAcademyId() != null) {
            Academy academy = RepositoryUtil.fetchById(academyRepository, request.getAcademyId(), "Academy");
            course.setAcademy(academy);
        }

        BeanUtils.copyProperties(request, course, getNullPropertyNames(request));
        Course savedCourse = courseRepository.save(course);

        return mapper.map(savedCourse, CourseResponse.class);
    }

    @Override
    public void delete(Long courseId) {
        RepositoryUtil.fetchById(courseRepository, courseId, "Course");
        courseRepository.deleteById(courseId);
    }

    @Override
    public CourseResponse getCourseById(Long courseId) {
        Course course = RepositoryUtil.fetchById(courseRepository, courseId, "Course");
        return mapper.map(course, CourseResponse.class);
    }

    @Override
    public Page<CourseResponse> getAllCourses(Pageable pageable) {
        Page<Course> coursePage = courseRepository.findAll(pageable);

        return coursePage.map(course -> mapper.map(course, CourseResponse.class));
    }


}
