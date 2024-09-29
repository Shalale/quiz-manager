package com.quizmanager.service.impl;

import com.quizmanager.dto.StudentRequest;
import com.quizmanager.dto.StudentResponse;
import com.quizmanager.entity.Student;
import com.quizmanager.exception.DuplicateEntryException;
import com.quizmanager.repository.StudentRepository;
import com.quizmanager.service.StudentService;
import com.quizmanager.utill.RepositoryUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;
    private final ModelMapper mapper;

    @Override
    public StudentResponse create(StudentRequest request) {
        try {
            Student student = mapper.map(request, Student.class);
            Student saved = repository.save(student);
            return mapper.map(saved, StudentResponse.class);
        }
        catch (DataIntegrityViolationException e) {
            throw new DuplicateEntryException("A student with the email " + request.getEmail() + " already exists.");
        }
    }

    @Override
    public StudentResponse getStudentById(Long studentId) {
        Student student = RepositoryUtil.fetchById(repository, studentId, "Student");
        return mapper.map(student, StudentResponse.class);
    }

    @Override
    public Page<StudentResponse> getAllStudents(Pageable pageable) {
        Page<Student> students = repository.findAll(pageable);
        return students.map(student -> mapper.map(student, StudentResponse.class));
    }

    @Override
    public List<StudentResponse> getAllStudentsByExamId(Long examId) {
        List<Student> students = repository.findAllByExamsId(examId);

        return students.stream().map(
                student -> mapper.map(student, StudentResponse.class)
        ).toList();
    }
}
