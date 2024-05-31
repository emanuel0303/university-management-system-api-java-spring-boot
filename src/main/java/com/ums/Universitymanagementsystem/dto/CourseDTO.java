package com.ums.Universitymanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class CourseDTO {
    private Integer course_id;

    @NotBlank(message = "Course name cannot be blank")
    @Size(max = 255, message = "Course name must be less than 255 characters")
    private String courseName;

    @Positive(message = "Credits must be a positive integer")
    private Integer credits;

    @NotBlank(message = "Prerequisites is required")
    @Size(max = 255, message = "Prerequisites must be less than 255 characters")
    private String prerequisites;

    @NotBlank(message = "Syllabus cannot be blank")
    private String syllabus;

    @Positive(message = "Enrollment capacity must be a positive integer")
    private Integer enrollmentCapacity;


}
