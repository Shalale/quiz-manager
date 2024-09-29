package com.quizmanager.service.impl;

import com.quizmanager.dto.ExamRequest;
import com.quizmanager.dto.ExamResponse;
import com.quizmanager.dto.ExamUpdateRequest;
import com.quizmanager.dto.StudentRequest;
import com.quizmanager.entity.Course;
import com.quizmanager.entity.Exam;
import com.quizmanager.entity.Student;
import com.quizmanager.repository.CourseRepository;
import com.quizmanager.repository.ExamRepository;
import com.quizmanager.repository.StudentRepository;
import com.quizmanager.service.ExamService;
import com.quizmanager.utill.RepositoryUtil;
import com.quizmanager.utill.UpdateHelper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public ExamResponse create(ExamRequest request) {
        Course course = RepositoryUtil.fetchById(courseRepository, request.getCourseId(), "Course");

        Exam exam = mapper.map(request, Exam.class);
        course.getExams().add(exam);
        exam.setCourse(course);

        // Set the exam reference in each question before saving
        if (exam.getQuestions() != null) {
            exam.getQuestions().forEach(question -> question.setExam(exam));
        }

        Exam savedExam = examRepository.save(exam);
        return mapper.map(savedExam, ExamResponse.class);
    }

    @Override
    public ExamResponse getExamById(Long id) {
        Exam exam = RepositoryUtil.fetchById(examRepository, id, "Exam");
        return mapper.map(exam, ExamResponse.class);
    }

    @Override
    public Page<ExamResponse> getAll(Pageable pageable) {
        Page<Exam> examPage = examRepository.findAll(pageable);
        return examPage.map(exam -> mapper.map(exam, ExamResponse.class));
    }

    @Override
    public Page<ExamResponse> getAllByCourse(Long id, Pageable pageable) {
        Page<Exam> examPage = examRepository.findAllByCourseId(id, pageable);
        return examPage.map(exam -> mapper.map(exam, ExamResponse.class));
    }

    @Override
    public ExamResponse update(ExamUpdateRequest request) {
        Exam exam = RepositoryUtil.fetchById(examRepository, request.getId(), "Exam");
        BeanUtils.copyProperties(request, exam, UpdateHelper.getNullPropertyNames(exam));
        Exam savedExam = examRepository.save(exam);
        return mapper.map(savedExam, ExamResponse.class);
    }

    @Override
    public void delete(Long id) {
        RepositoryUtil.fetchById(examRepository, id, "Exam");
        examRepository.deleteById(id);
    }

    @Override //@todo: optimize this method
    @Transactional
    public ExamResponse addStudentsToExam(Long examId, List<StudentRequest> studentRequestList) {
        Exam exam = RepositoryUtil.fetchById(examRepository, examId, "Exam");

        Set<Student> studentsToAdd = studentRequestList.stream()
                .map(userRequest -> studentRepository.findByEmail(userRequest.getEmail())
                        .orElseGet(() -> {
                            Student newStudent = Student.builder()
                                    .name(userRequest.getName())
                                    .surname(userRequest.getSurname())
                                    .email(userRequest.getEmail())
                                    .build();

                            return studentRepository.save(newStudent);
                        }))
                .collect(Collectors.toSet());

        // Check if the student is already associated with the exam before adding
        for (Student student : studentsToAdd) {
            if (!exam.getStudents().contains(student)) {
                exam.getStudents().add(student);

                // Also update the student's exam list
                if (student.getExams() == null) {
                    student.setExams(new ArrayList<>());
                }
                if (!student.getExams().contains(exam)) {
                    student.getExams().add(exam);
                }
            }
        }

        // Save the updated exam
        Exam savedExam = examRepository.save(exam);
        return mapper.map(savedExam, ExamResponse.class);
    }

}
