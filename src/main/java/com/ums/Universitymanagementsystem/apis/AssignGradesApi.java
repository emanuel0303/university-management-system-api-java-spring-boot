package com.ums.Universitymanagementsystem.apis;

import com.ums.Universitymanagementsystem.dto.GradeDTO;
import com.ums.Universitymanagementsystem.entity.Enrollment;
import com.ums.Universitymanagementsystem.entity.Grade;
import com.ums.Universitymanagementsystem.service.EnrollmentService;
import com.ums.Universitymanagementsystem.service.GradeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssignGradesApi {

    private final GradeService gradeService;
    private final ModelMapper modelMapper;
    private final EnrollmentService enrollmentService;

    @Autowired
    public AssignGradesApi(GradeService gradeService,
                           ModelMapper modelMapper,
                           EnrollmentService enrollmentService) {

        this.gradeService = gradeService;
        this.modelMapper = modelMapper;
        this.enrollmentService = enrollmentService;
    }




    public ResponseEntity<String> addGrade(GradeDTO gradeDTO) {
        try {
            // Validate enrollment existence
            Enrollment enrollment = enrollmentService.findEnrollmentById(gradeDTO.getEnroll_id());
            if (enrollment == null) {
                return ResponseEntity.badRequest().body("Invalid enrollment ID");
            }
            //ModelMapper to map GradeDTO to Grade entity
            Grade grade = modelMapper.map(gradeDTO, Grade.class);
            grade.setEnrollment(enrollment);

            // Save the grade
            gradeService.saveGrade(grade);

            return ResponseEntity.ok("Grade assigned successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred during grade assignment");
        }
    }
}

