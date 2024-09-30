package com.quizmanager.utill;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class FilterUtils {

    public static <T> void addPredicateIfNotNull(CriteriaBuilder cb, List<Predicate> predicates,
                                                 Root<T> root, String field, Object value) {
        if (value != null) {
            predicates.add(cb.like(root.get(field), "%" + value + "%"));
        }
    }

    public static <T> void addJoinPredicateIfNotNull(CriteriaBuilder cb, List<Predicate> predicates,
                                                     Root<T> root, String joinField, String field, Object value) {
        if (value != null) {
            predicates.add(cb.like(root.join(joinField).get(field), "%" + value + "%"));
        }
    }
}
