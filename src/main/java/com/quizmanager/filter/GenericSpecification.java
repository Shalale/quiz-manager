package com.quizmanager.filter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GenericSpecification<T> implements Specification<T> {
    private final List<FilterCriteria<?>> criteriaList = new ArrayList<>();

    public void add(FilterCriteria<?> criteria) {
        criteriaList.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        for (FilterCriteria<?> criteria : criteriaList) {
            FilterUtils.addPredicate(cb, predicates, root,
                    criteria.getOperation(),
                    criteria.getFieldName(),
                    criteria.getValue(),
                    criteria.getValueTo());
        }
        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
