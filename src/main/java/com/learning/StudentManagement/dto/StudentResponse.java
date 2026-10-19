package com.learning.StudentManagement.dto;

import java.time.LocalDateTime;

public record StudentResponse(
        Long id,
        String name,
        String email,
        Integer age,
        String course,
        String department,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

}
