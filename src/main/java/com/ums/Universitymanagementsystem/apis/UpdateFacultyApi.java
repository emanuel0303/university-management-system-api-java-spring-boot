package com.ums.Universitymanagementsystem.apis;

import com.ums.Universitymanagementsystem.dto.FacultyDTO;
import com.ums.Universitymanagementsystem.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UpdateFacultyApi {

    private final FacultyService facultyService;

    @Autowired
    public UpdateFacultyApi(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    public ResponseEntity<String> updateFaculty(Integer faculty_id, FacultyDTO updatedFacultyDTO) {
        // Pass the ID and updated faculty DTO to the service method for updating
        String message = facultyService.updateFaculty(faculty_id, updatedFacultyDTO);
        return ResponseEntity.ok(message);
    }


}
