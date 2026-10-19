package com.learning.StudentManagement.dto;

import jakarta.validation.constraints.*;

public record StudentRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name must not exceed 100 characters")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Enter a valid email address")
        @Size(max = 150, message = "Email must not exceed 150 characters")
        String email,

        @NotNull(message = "Age is required")
        @Min(value = 16, message = "Age must be at least 16")
        @Max(value = 100, message = "Age must not exceed 100")
        Integer age,

        @NotBlank(message = "Course is required")
        @Size(max = 100, message = "Course must not exceed 100 characters")
        String course,

        @NotBlank(message = "Department is required")
        @Size(max = 100, message = "Department must not exceed 100 characters")
        String department
) {

}
