package com.quizmanager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseFilter {
    private String name;
    private String instructor;
    private String academyName;
    private String examTitle;
}
