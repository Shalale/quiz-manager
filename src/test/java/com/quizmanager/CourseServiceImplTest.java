package com.quizmanager.service.impl;

import com.quizmanager.dto.CourseRequest;
import com.quizmanager.dto.CourseResponse;
import com.quizmanager.dto.CourseUpdateRequest;
import com.quizmanager.entity.Course;
import com.quizmanager.repository.CourseRepository;
import com.quizmanager.service.CourseService;
import com.quizmanager.utill.RepositoryUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private CourseServiceImpl courseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {
        CourseRequest request = new CourseRequest();
        Course course = new Course();
        Course savedCourse = new Course();
        CourseResponse response = new CourseResponse();

        when(mapper.map(request, Course.class)).thenReturn(course);
        when(courseRepository.save(course)).thenReturn(savedCourse);
        when(mapper.map(savedCourse, CourseResponse.class)).thenReturn(response);

        CourseResponse result = courseService.create(request);

        assertNotNull(result);
        verify(courseRepository).save(course);
    }

    @Test
    public void testUpdate() {
        Long courseId = 1L;
        CourseUpdateRequest request = new CourseUpdateRequest();
        request.setId(courseId);
        request.setName("newValue"); // Set fields to be updated
        Course existingCourse = new Course();
        existingCourse.setName("oldValue"); // Ensure this matches your entity's structure
        Course updatedCourse = new Course();
        CourseResponse response = new CourseResponse();

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(existingCourse));
        when(courseRepository.save(any(Course.class))).thenReturn(updatedCourse);
        when(mapper.map(existingCourse, CourseResponse.class)).thenReturn(response);

        // Perform the update
        CourseResponse result = courseService.update(request);

        // Assertions
        assertNotNull(result, "The result should not be null");
        verify(courseRepository).save(existingCourse);
    }

    @Test
    public void testDelete() {
        Long courseId = 1L;
        Course course = new Course(); // Provide a default course if needed

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        doNothing().when(courseRepository).deleteById(courseId);

        courseService.delete(courseId);

        verify(courseRepository).deleteById(courseId);
    }

    @Test
    public void testGetCourseById() {
        Long courseId = 1L;
        Course course = new Course();
        CourseResponse response = new CourseResponse();

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(mapper.map(course, CourseResponse.class)).thenReturn(response);

        CourseResponse result = courseService.getCourseById(courseId);

        assertNotNull(result);
        verify(courseRepository).findById(courseId);
    }

    @Test
    public void testGetAllCourses() {
        Pageable pageable = Pageable.unpaged();
        Course course = new Course();
        CourseResponse response = new CourseResponse();
        Page<Course> coursePage = new PageImpl<>(List.of(course));

        when(courseRepository.findAll(pageable)).thenReturn(coursePage);
        when(mapper.map(course, CourseResponse.class)).thenReturn(response);

        Page<CourseResponse> result = courseService.getAllCourses(pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(courseRepository).findAll(pageable);
    }
}