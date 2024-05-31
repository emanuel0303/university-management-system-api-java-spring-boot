package com.ums.Universitymanagementsystem.controller;

import com.ums.Universitymanagementsystem.apis.*;
import com.ums.Universitymanagementsystem.dto.FacultyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final AddFacultyApi addFacultyApi;
    private final UpdateFacultyApi updateFacultyApi;
    private final AssignFacultyApi assignFacultyApi;
    private final GetFacultyApi getFacultyApi;
    private final DeleteFacultyApi deleteFacultyApi;

    @Autowired
    public FacultyController(AddFacultyApi addFacultyApi,
                             UpdateFacultyApi updateFacultyApi,
                             AssignFacultyApi assignFacultyApi,
                             GetFacultyApi getFacultyApi,
                             DeleteFacultyApi deleteFacultyApi) {
        this.addFacultyApi = addFacultyApi;
        this.updateFacultyApi = updateFacultyApi;
        this.assignFacultyApi = assignFacultyApi;
        this.getFacultyApi = getFacultyApi;
        this.deleteFacultyApi = deleteFacultyApi;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFaculty(@Validated @RequestBody FacultyDTO facultyDTO, BindingResult result) {
        if (result.hasErrors()) {
            // If there are validation errors, construct error message and return bad request response
            String errorMessage = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .findFirst()
                    .orElse("Validation failed");
            return ResponseEntity.badRequest().body(errorMessage);
        }

        // Proceed with adding the faculty
        return addFacultyApi.addFaculty(facultyDTO);
    }

    @GetMapping("/get/{faculty_id}")
    public ResponseEntity<?> getFaculty(@PathVariable Integer faculty_id) {
        return getFacultyApi.getFaculty(faculty_id);
    }

    @PutMapping("/update/{faculty_id}")
    public ResponseEntity<String> updateFaculty(@PathVariable Integer faculty_id, @Validated @RequestBody FacultyDTO updatedFacultyDTO) {
        return updateFacultyApi.updateFaculty(faculty_id, updatedFacultyDTO);
    }

    @PostMapping("/assign/{faculty_id}/courses/{course_id}")
    public ResponseEntity<String> assignFacultyToCourse(@PathVariable Integer faculty_id, @PathVariable Integer course_id) {
        return assignFacultyApi.assignFacultyToCourse(faculty_id, course_id);
    }

    @DeleteMapping("/delete/{faculty_id}")
    public ResponseEntity<String> deleteFaculty(@PathVariable Integer faculty_id) {
        return deleteFacultyApi.deleteFaculty(faculty_id);
    }
}
