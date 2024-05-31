package com.ums.Universitymanagementsystem.controller;

import com.ums.Universitymanagementsystem.apis.EnrollStudentApi;
import com.ums.Universitymanagementsystem.dto.EnrollmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("enrollment")
public class EnrollmentController {

    private final EnrollStudentApi enrollStudentApi;

    @Autowired
    public EnrollmentController(EnrollStudentApi enrollStudentApi) {
        this.enrollStudentApi = enrollStudentApi;
    }

    @PostMapping("/enroll")
    public ResponseEntity<String> enrollStudent(@Validated @RequestBody EnrollmentDTO enrollmentDTO, BindingResult result) {
        if (result.hasErrors()) {
            // If there are validation errors, construct error message and return bad request response
            String errorMessage = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .findFirst()
                    .orElse("Validation failed");
            return ResponseEntity.badRequest().body(errorMessage);
        }
        return enrollStudentApi.enrollStudent(enrollmentDTO);
    }
}
