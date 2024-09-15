package com.quizmanager.repository;

import com.quizmanager.entity.Academy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Academy, Long> {
}
