package com.ums.Universitymanagementsystem.controller;


import com.ums.Universitymanagementsystem.apis.AssignGradesApi;
import com.ums.Universitymanagementsystem.dto.GradeDTO;
import com.ums.Universitymanagementsystem.entity.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private final AssignGradesApi assignGradesApi;

    @Autowired
    public GradeController(AssignGradesApi assignGradesApi) {
        this.assignGradesApi = assignGradesApi;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addGrade(@Validated @RequestBody GradeDTO gradeDTO, BindingResult result) {
        if (result.hasErrors()) {
            // If there are validation errors, construct error message and return bad request response
            String errorMessage = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .findFirst()
                    .orElse("Validation failed");
            return ResponseEntity.badRequest().body(errorMessage);
        }

        // Proceed with adding the course
        return assignGradesApi.addGrade(gradeDTO);
    }
}
