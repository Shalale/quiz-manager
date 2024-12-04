package com.quizmanager.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
/**
 * Represents a single filter criterion. Example:
 * Field: name
 * Value: "Laptop"
 * Operation: LIKE
 * <T> allows the class to work with different data types,such as String, Integer, BigDecimal, or LocalDate.
 */
public class FilterCriteria<T>{
    private String fieldName;
    private T value;
    private T valueTo;
    private FilterOperation operation;
}
