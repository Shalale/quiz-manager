package com.quizmanager;

import com.quizmanager.dto.StudentRequest;
import com.quizmanager.dto.StudentResponse;
import com.quizmanager.entity.Student;
import com.quizmanager.repository.StudentRepository;
import com.quizmanager.service.impl.StudentServiceImpl;
import com.quizmanager.utill.RepositoryUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

    @Mock
    private StudentRepository repository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    public void testCreateWithValidRequest() {
        StudentRequest request = new StudentRequest();
        Student student = new Student();
        Student savedStudent = new Student();
        StudentResponse response = new StudentResponse();

        when(mapper.map(request, Student.class)).thenReturn(student);
        when(repository.save(student)).thenReturn(savedStudent);
        when(mapper.map(savedStudent, StudentResponse.class)).thenReturn(response);

        StudentResponse result = studentService.create(request);

        assertNotNull(result, "The result should not be null");
        verify(repository).save(student);
        verify(mapper).map(savedStudent, StudentResponse.class);
    }

    @Test
    public void testGetStudentByIdWithExistingStudent() {
        Long studentId = 1L;
        Student student = new Student();
        StudentResponse response = new StudentResponse();

        when(RepositoryUtil.fetchById(repository, studentId)).thenReturn(student);
        when(mapper.map(student, StudentResponse.class)).thenReturn(response);

        StudentResponse result = studentService.getStudentById(studentId);

        assertNotNull(result, "The result should not be null");
        verify(mapper).map(student, StudentResponse.class);
    }

    @Test
    public void testGetStudentByIdWithNonExistentStudent() {
        Long studentId = 1L;

        when(RepositoryUtil.fetchById(repository, studentId)).thenThrow(new RuntimeException("Entity not found with id: " + studentId));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            studentService.getStudentById(studentId);
        });

        assertEquals("Entity not found with id: 1", thrown.getMessage());
    }

    @Test
    public void testGetAllStudentsByExamIdWithStudents() {
        Long examId = 1L;
        Student student1 = new Student();
        Student student2 = new Student();
        StudentResponse response1 = new StudentResponse();
        StudentResponse response2 = new StudentResponse();
        List<Student> students = List.of(student1, student2);

        when(repository.findAllByExam_Id(examId)).thenReturn(students);
        when(mapper.map(student1, StudentResponse.class)).thenReturn(response1);
        when(mapper.map(student2, StudentResponse.class)).thenReturn(response2);

        List<StudentResponse> result = studentService.getAllStudentsByExamId(examId);

        assertNotNull(result, "The result should not be null");
        assertEquals(2, result.size(), "The result size should be 2");
        assertTrue(result.contains(response1), "The result should contain response1");
        assertTrue(result.contains(response2), "The result should contain response2");
    }

    @Test
    public void testGetAllStudentsByExamIdWithNoStudents() {
        Long examId = 1L;

        when(repository.findAllByExam_Id(examId)).thenReturn(Collections.emptyList());

        List<StudentResponse> result = studentService.getAllStudentsByExamId(examId);

        assertNotNull(result, "The result should not be null");
        assertTrue(result.isEmpty(), "The result should be empty");
    }
}
