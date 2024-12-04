package com.quizmanager.repository;

import com.quizmanager.entity.Academy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AcademyRepository extends JpaRepository<Academy, Long>, JpaSpecificationExecutor<Academy> {
}
