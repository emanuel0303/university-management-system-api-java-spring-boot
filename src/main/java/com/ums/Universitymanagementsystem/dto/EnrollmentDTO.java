package com.ums.Universitymanagementsystem.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;


@Data
@Validated
public class EnrollmentDTO {

    private Integer enroll_id;

    @NotNull(message = "Student ID is required")
    private Integer stud_id;

    @NotNull(message = "Course ID is required")
    private Integer course_id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Enrollment date is required")
    private LocalDate enrollmentDate;

    private GradeDTO grade;
}
