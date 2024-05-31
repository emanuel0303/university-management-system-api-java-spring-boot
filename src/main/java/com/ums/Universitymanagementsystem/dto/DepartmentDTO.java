package com.ums.Universitymanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class DepartmentDTO {

    private Integer dept_id;

    @NotBlank(message = "Department name cannot be blank")
    @Size(max = 255, message = "Department name must be less than 255 characters")
    private String deptName;

}
