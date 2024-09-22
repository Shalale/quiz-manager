package com.quizmanager;

import com.quizmanager.dto.ExamRequest;
import com.quizmanager.dto.ExamResponse;
import com.quizmanager.dto.ExamUpdateRequest;
import com.quizmanager.entity.Exam;
import com.quizmanager.repository.ExamRepository;
import com.quizmanager.service.impl.ExamServiceImpl;
import com.quizmanager.utill.UpdateHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExamServiceImplTest {

    @Mock
    private ExamRepository examRepository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private ExamServiceImpl examService;

    @Test
    public void testCreateWithNullRequest() {
        ExamResponse response = examService.create(null);
        assertNull(response, "The result should be null when request is null");
    }

    @Test
    public void testGetExamByIdWithNonExistentExam() {
        Long examId = 1L;

        when(examRepository.findById(examId)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            examService.getExamById(examId);
        });

        assertEquals("Entity not found with id: 1", thrown.getMessage());
    }

    @Test
    public void testGetAllByCourseWithNoExams() {
        Long courseId = 1L;
        Pageable pageable = Pageable.unpaged();

        Page<Exam> examPage = new PageImpl<>(Collections.emptyList());

        when(examRepository.findAllByCourseId(courseId, pageable)).thenReturn(examPage);

        Page<ExamResponse> result = examService.getAllByCourse(courseId, pageable);

        assertNotNull(result, "The result should not be null");
        assertTrue(result.getContent().isEmpty(), "The result should be empty");
    }

    @Test
    public void testUpdateWithNonExistentExam() {
        Long examId = 1L;
        ExamUpdateRequest request = new ExamUpdateRequest();
        request.setId(examId);

        when(examRepository.findById(examId)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            examService.update(request);
        });

        assertEquals("Entity not found with id: 1", thrown.getMessage());
    }

    @Test
    public void testUpdateWithAllPropertiesNull() {
        Long examId = 1L;
        ExamUpdateRequest request = new ExamUpdateRequest();
        request.setId(examId);
        Exam existingExam = new Exam();
        Exam updatedExam = new Exam();
        ExamResponse response = new ExamResponse();

        when(examRepository.findById(examId)).thenReturn(Optional.of(existingExam));
        when(examRepository.save(existingExam)).thenReturn(updatedExam);
        when(mapper.map(updatedExam, ExamResponse.class)).thenReturn(response);
        when(UpdateHelper.getNullPropertyNames(request)).thenReturn(new String[]{});

        ExamResponse result = examService.update(request);

        assertNotNull(result, "The result should not be null");
        verify(examRepository, never()).save(any());
    }

    @Test
    public void testDeleteWithNonExistentExam() {
        Long examId = 1L;

        when(examRepository.findById(examId)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            examService.delete(examId);
        });

        assertEquals("Entity not found with id: 1", thrown.getMessage());
    }

    @Test
    public void testCreateWithValidInput() {
        ExamRequest request = new ExamRequest();
        Exam exam = new Exam();
        Exam savedExam = new Exam();
        ExamResponse response = new ExamResponse();

        when(mapper.map(request, Exam.class)).thenReturn(exam);
        when(examRepository.save(exam)).thenReturn(savedExam);
        when(mapper.map(savedExam, ExamResponse.class)).thenReturn(response);

        ExamResponse result = examService.create(request);

        assertNotNull(result, "The result should not be null");
        verify(examRepository).save(exam);
    }

    @Test
    public void testGetAllByCourseWithMultipleExams() {
        Long courseId = 1L;
        Pageable pageable = Pageable.unpaged();
        Exam exam1 = new Exam();
        Exam exam2 = new Exam();
        ExamResponse response1 = new ExamResponse();
        ExamResponse response2 = new ExamResponse();
        Page<Exam> examPage = new PageImpl<>(List.of(exam1, exam2));

        when(examRepository.findAllByCourseId(courseId, pageable)).thenReturn(examPage);
        when(mapper.map(exam1, ExamResponse.class)).thenReturn(response1);
        when(mapper.map(exam2, ExamResponse.class)).thenReturn(response2);

        Page<ExamResponse> result = examService.getAllByCourse(courseId, pageable);

        assertNotNull(result, "The result should not be null");
        assertEquals(2, result.getTotalElements(), "The result should contain two elements");
    }
}
