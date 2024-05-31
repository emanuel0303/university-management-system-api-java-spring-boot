package com.ums.Universitymanagementsystem.apis;

import com.ums.Universitymanagementsystem.exception.StudentNotFoundException;
import com.ums.Universitymanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DeleteStudentApi {

    private final StudentService studentService;

    @Autowired
    public DeleteStudentApi(StudentService studentService) {
        this.studentService = studentService;
    }

    public ResponseEntity<String> deleteStudent(Integer id) {
        try {
            studentService.deleteStudentById(id);
            return ResponseEntity.ok("Student with ID " + id + " deleted successfully");
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found with ID: " + id);
        }
    }
}
