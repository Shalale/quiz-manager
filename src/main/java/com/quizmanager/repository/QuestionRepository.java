package com.quizmanager.repository;

import com.quizmanager.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> getAllByExamId(Long examId);
}
