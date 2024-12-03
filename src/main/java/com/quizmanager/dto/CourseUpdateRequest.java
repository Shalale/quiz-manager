package com.quizmanager.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CourseUpdateRequest extends CourseRequest {
    private Long id;
    String name;
    String instructor;
    Long academyId;
}
