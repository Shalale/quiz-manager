package com.quizmanager.dto.academyDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AcademyLoginResponse {
    String message;
    String token; // For authentication
}

