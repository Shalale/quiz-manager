package com.quizmanager.specification;

import com.quizmanager.entity.Academy;
import com.quizmanager.entity.Course;
import com.quizmanager.entity.Exam;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class CourseSearchSpecification {
    public static Specification<Course> searchCourse(String search) {
        return (root, query, criteriaBuilder) -> {
            if (search == null || search.trim().isEmpty()) {
                return criteriaBuilder.conjunction();  // Return all results if search is empty
            }

            // Predicate for course name
            Predicate namePredicate = criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")),
                    likePattern(search)
            );

            // Predicate for instructor
            Predicate instructorPredicate = criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("instructor")),
                    likePattern(search)
            );

            // Join with Academy entity for academy name
            Join<Course, Academy> academyJoin = root.join("academy", JoinType.LEFT);
            Predicate academyNamePredicate = criteriaBuilder.like(
                    criteriaBuilder.lower(academyJoin.get("name")),
                    likePattern(search)
            );

            // Join with Exam entity for exam name
            Join<Course, Exam> examJoin = root.join("exams", JoinType.LEFT);
            Predicate examNamePredicate = criteriaBuilder.like(
                    criteriaBuilder.lower(examJoin.get("name")),
                    likePattern(search)
            );

            // Return combined predicates using OR
            return criteriaBuilder.or(
                    namePredicate,
                    instructorPredicate,
                    academyNamePredicate,
                    examNamePredicate
            );
        };
    }

    // Utility method for wrapping search term with '%' for wildcard search
    private static String likePattern(String searchTerm) {
        return "%" + searchTerm.toLowerCase() + "%";
    }
}
