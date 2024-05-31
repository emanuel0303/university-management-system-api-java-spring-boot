package com.ums.Universitymanagementsystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;


@Data
@Validated
public class GradeDTO {

    private Integer grade_id;

    @NotNull(message = "Enrollment ID is required")
    private Integer enroll_id;

    @NotNull(message = "Score is mandatory")
    private Float score;
}
