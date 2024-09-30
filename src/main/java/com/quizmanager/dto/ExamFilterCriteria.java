package com.quizmanager.dto;

import com.quizmanager.entity.Course;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

public class ExamFilterCriteria {

    String title;
    String courseName;
}
