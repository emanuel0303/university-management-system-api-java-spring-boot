package com.ums.Universitymanagementsystem.controller;

import com.ums.Universitymanagementsystem.apis.*;
import com.ums.Universitymanagementsystem.dto.CourseDTO;
import com.ums.Universitymanagementsystem.dto.GradeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final AddCourseApi addCourseApi;
    private final GetCourseApi getCourseApi;
    private final UpdateCourseApi updateCourseApi;
    private final DeleteCourseApi deleteCourseApi;
    private final AssignGradesApi assignGradesApi;

    @Autowired
    public CourseController(AddCourseApi addCourseApi, GetCourseApi getCourseApi,
                            UpdateCourseApi updateCourseApi,
                            DeleteCourseApi deleteCourseApi,
                            AssignGradesApi assignGradesApi
                            )
    {
        this.addCourseApi = addCourseApi;
        this.getCourseApi = getCourseApi;
        this.updateCourseApi = updateCourseApi;
        this.deleteCourseApi = deleteCourseApi;
        this.assignGradesApi = assignGradesApi;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCourse(@Validated @RequestBody CourseDTO courseDTO, BindingResult result) {
        if (result.hasErrors()) {
            // If there are validation errors, construct error message and return bad request response
            String errorMessage = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .findFirst()
                    .orElse("Validation failed");
            return ResponseEntity.badRequest().body(errorMessage);
        }

        // Proceed with adding the course
        return addCourseApi.addCourse(courseDTO);
    }

    @GetMapping("/get/{course_id}")
    public ResponseEntity<?> getCourse(@PathVariable Integer course_id) {
        return getCourseApi.getCourse(course_id);
    }

    @PutMapping("/update/{course_id}")
    public ResponseEntity<String> updateCourse(@PathVariable Integer course_id, @Validated @RequestBody CourseDTO updatedCourseDTO) {
        return updateCourseApi.updateCourse(course_id, updatedCourseDTO);
    }

    @DeleteMapping("/delete/{course_id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Integer course_id) {
        return deleteCourseApi.deleteCourse(course_id);
    }



}
