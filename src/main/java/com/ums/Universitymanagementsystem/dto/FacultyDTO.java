package com.ums.Universitymanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class FacultyDTO {

    private Integer faculty_id;

    @NotBlank(message = "Name is required")
    private String facultyName;



    @NotBlank(message = "Qualifications are required")
    private String qualifications;

    private Integer dept_id;
}
