package com.ums.Universitymanagementsystem.apis;

import com.ums.Universitymanagementsystem.dto.FacultyDTO;

import com.ums.Universitymanagementsystem.entity.Faculty;
import com.ums.Universitymanagementsystem.service.FacultyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddFacultyApi {
    private final FacultyService facultyService;

    @Autowired
    public AddFacultyApi(FacultyService facultyService) {
        this.facultyService = facultyService;


    }

    public ResponseEntity<String> addFaculty(FacultyDTO facultyDTO) {
        // Assuming the Department ID is provided in the FacultyDTO
        Faculty faculty = facultyService.createFaculty(facultyDTO);
        String successMessage = "Faculty member added successfully.\n" +
                "Faculty ID: " + faculty.getFaculty_id() + "\n" +
                "Faculty Name: " + faculty.getFacultyName() + "\n";
        if (faculty.getDepartment() != null) {
            successMessage += "Department Name: " + faculty.getDepartment().getDeptName();
        } else {
            successMessage += "Department ID: " + facultyDTO.getDept_id();
        }
        return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
    }
}
