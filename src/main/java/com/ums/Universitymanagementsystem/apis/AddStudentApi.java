package com.ums.Universitymanagementsystem.apis;

import com.ums.Universitymanagementsystem.dto.StudentDTO;
import com.ums.Universitymanagementsystem.entity.Student;
import com.ums.Universitymanagementsystem.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddStudentApi {
    private final StudentService studentService;
    private final ModelMapper modelMapper;

    @Autowired
    public AddStudentApi(StudentService studentService, ModelMapper modelMapper) {
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<String> addStudent(StudentDTO studentDTO) {
        Student student = modelMapper.map(studentDTO, Student.class);
        // Call the StudentService to add the student
        studentService.createStudent(student);
        String successMessage = "Student added successfully..\n" +
                "Student ID: " + student.getStud_id() + "\n" +
                "Student Name: " + student.getFirstName() + " " + student.getLastName() + "\n" +
                "Major: " + student.getMajor();
        return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
    }
}
