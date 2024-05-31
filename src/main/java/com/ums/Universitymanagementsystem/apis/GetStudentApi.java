package com.ums.Universitymanagementsystem.apis;


import com.ums.Universitymanagementsystem.dto.EnrollmentDTO;
import com.ums.Universitymanagementsystem.dto.StudentDTO;
import com.ums.Universitymanagementsystem.entity.Enrollment;
import com.ums.Universitymanagementsystem.entity.Student;
import com.ums.Universitymanagementsystem.exception.StudentNotFoundException;
import com.ums.Universitymanagementsystem.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class GetStudentApi {

    private final StudentService studentService;
    private final ModelMapper modelMapper;


    @Autowired
    public GetStudentApi(StudentService studentService,
                         ModelMapper modelMapper) {
        this.studentService = studentService;
        this.modelMapper = modelMapper;

    }

    public ResponseEntity<?> getStudent(Integer stud_id) {
        try {
            Student student = studentService.getStudentById(stud_id);
            StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
            return ResponseEntity.ok(studentDTO);
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(404).body("ID not found in database");
        }
    }
}
