package com.quizmanager.non_optimal_codes;

import com.quizmanager.dto.CourseFilter;
import com.quizmanager.entity.Course;
import com.quizmanager.non_optimal_codes.FilterUtils;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;

public class CourseFilterSpecification {
    public static Specification<Course> filterBy(CourseFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filter by name
            FilterUtils.addPredicateIfNotNull(cb, predicates, root, "name", filter.getName());

            // Filter by instructor
            FilterUtils.addPredicateIfNotNull(cb, predicates, root, "instructor", filter.getInstructor());

            // Filter by academy name
            FilterUtils.addJoinPredicateIfNotNull(cb, predicates, root, "academy", "name", filter.getAcademyName());

            // Filter by exam title
            FilterUtils.addJoinPredicateIfNotNull(cb, predicates, root, "exams", "title", filter.getExamTitle());

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
