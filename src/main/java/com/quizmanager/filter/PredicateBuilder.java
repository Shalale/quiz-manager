package com.quizmanager.filter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

//Defines a contract for building a predicate.
//Instead of hardcoding if/switch logic, you dynamically assign a builder for each operation in the PREDICATE_BUILDERS map.
@FunctionalInterface
public interface PredicateBuilder {
     void build(CriteriaBuilder cb, List<Predicate> predicates, Root<?> root, String fieldName, Object value, Object valueTo);
}
