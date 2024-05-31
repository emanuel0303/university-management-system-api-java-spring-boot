package com.ums.Universitymanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.time.LocalTime;

@Data
@Validated
public class TimeTableDTO {


    private Integer tt_id;

    @NotNull(message = "Course ID is mandatory")
    private Integer course_id;

    @NotNull(message = "Faculty ID is mandatory")
    private Integer faculty_id;

    @NotNull(message = "Classroom ID is mandatory")
    private Integer class_id;

    @NotBlank(message = "Day is mandatory")
    private String day;

    @NotNull(message = "Time is mandatory")
    private LocalTime time;
}
