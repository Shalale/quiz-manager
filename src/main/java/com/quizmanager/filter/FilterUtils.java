package com.quizmanager.filter;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.EnumMap;
import java.util.List;

public class FilterUtils {
    private static final EnumMap<FilterOperation, PredicateBuilder> PREDICATE_BUILDERS = new EnumMap<>(FilterOperation.class);

    static {
        PREDICATE_BUILDERS.put(FilterOperation.EQUAL, (cb, predicates, root, fieldName, value, valueTo) ->
                predicates.add(cb.equal(root.get(fieldName), value))
        );

        PREDICATE_BUILDERS.put(FilterOperation.LIKE, (cb, predicates, root, fieldName, value, valueTo) ->
                predicates.add(cb.like(root.get(fieldName).as(String.class), "%" + value + "%"))
        );

        PREDICATE_BUILDERS.put(FilterOperation.GREATER_THAN, (cb, predicates, root, fieldName, value, valueTo) ->
                predicates.add(cb.greaterThan(root.get(fieldName).as(Comparable.class), (Comparable) value))
        );

        PREDICATE_BUILDERS.put(FilterOperation.LESS_THAN, (cb, predicates, root, fieldName, value, valueTo) ->
                predicates.add(cb.lessThan(root.get(fieldName).as(Comparable.class), (Comparable) value))
        );

        PREDICATE_BUILDERS.put(FilterOperation.BETWEEN, (cb, predicates, root, fieldName, value, valueTo) ->
                predicates.add(cb.between(root.get(fieldName).as(Comparable.class), (Comparable) value, (Comparable) valueTo))
        );
    }

    public static <T> void addPredicate(CriteriaBuilder cb,
                                        List<Predicate> predicates,
                                        Root<T> root,
                                        FilterOperation operation,
                                        String fieldName,
                                        Object value,
                                        Object valueTo) {
        PredicateBuilder builder = PREDICATE_BUILDERS.get(operation);
        if (builder == null) {
            throw new UnsupportedOperationException("Filter operation not supported: " + operation);
        }
        builder.build(cb, predicates, root, fieldName, value, valueTo);
    }
}
