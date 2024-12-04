package com.quizmanager.filter;

/**
 * Enum for filter operations. If I need additional filter like "not equal" I can add it here.
 */
public enum FilterOperation {
    EQUAL,
    LIKE,
    GREATER_THAN,
    LESS_THAN,
    BETWEEN
}
