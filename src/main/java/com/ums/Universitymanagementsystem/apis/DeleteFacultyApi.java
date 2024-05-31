package com.ums.Universitymanagementsystem.apis;

import com.ums.Universitymanagementsystem.exception.FacultyNotFoundException;
import com.ums.Universitymanagementsystem.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DeleteFacultyApi {
    private final FacultyService facultyService;

    @Autowired
    public DeleteFacultyApi(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    public ResponseEntity<String> deleteFaculty(Integer faculty_id) {
        try {
            facultyService.deleteFacultyById(faculty_id);
            return ResponseEntity.ok("Faculty with ID " + faculty_id + " deleted successfully");
        } catch (FacultyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faculty not found with ID: " + faculty_id);
        }
    }
}
