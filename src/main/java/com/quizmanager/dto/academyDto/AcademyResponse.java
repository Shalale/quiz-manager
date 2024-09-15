package com.quizmanager.dto.academyDto;

import com.quizmanager.dto.CourseResponse;
import com.quizmanager.entity.Course;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AcademyResponse {
    Long id;
    String name;
    String email;
    String logo;
    Set<CourseResponse> courses;
}
