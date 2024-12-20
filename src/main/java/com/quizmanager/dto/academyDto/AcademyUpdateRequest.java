package com.quizmanager.dto.academyDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AcademyUpdateRequest {
    Long id;
    String name;
    String email;
    String logo;
}
