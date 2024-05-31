package com.ums.Universitymanagementsystem.apis;

import com.ums.Universitymanagementsystem.dto.FacultyDTO;
import com.ums.Universitymanagementsystem.entity.Faculty;
import com.ums.Universitymanagementsystem.exception.FacultyNotFoundException;
import com.ums.Universitymanagementsystem.service.FacultyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class GetFacultyApi {
    private final FacultyService facultyService;
    private final ModelMapper modelMapper;

    @Autowired
    public GetFacultyApi(FacultyService facultyService, ModelMapper modelMapper) {
        this.facultyService = facultyService;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<?> getFaculty(Integer faculty_id) {
        try {
            Faculty faculty = facultyService.getFacultyById(faculty_id);
            FacultyDTO facultyDTO = modelMapper.map(faculty, FacultyDTO.class);
            return ResponseEntity.ok(facultyDTO);
        } catch (FacultyNotFoundException e) {
            return ResponseEntity.status(404).body("ID not found in database");
        }
    }
}
