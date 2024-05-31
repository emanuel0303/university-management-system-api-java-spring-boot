package com.ums.Universitymanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Validated
public class ExamDTO {

    private Integer exam_id;

    @NotNull(message = "Date Cannot be Empty")
    private LocalDate date;


    @NotBlank(message = "Venue cannot be Blank")
    private String exam_venue;

    @NotNull(message = "Course Id is required")
    private Integer course_id;

    @NotNull(message = "Time is Required")
    private LocalTime time;


}
