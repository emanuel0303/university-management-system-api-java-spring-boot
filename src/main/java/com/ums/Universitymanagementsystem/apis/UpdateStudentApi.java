package com.ums.Universitymanagementsystem.apis;

import com.ums.Universitymanagementsystem.dto.StudentDTO;
import com.ums.Universitymanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UpdateStudentApi {
    private final StudentService studentService;

    @Autowired
    public UpdateStudentApi(StudentService studentService) {
        this.studentService = studentService;
    }

    public ResponseEntity<String> updateStudent(Integer id, StudentDTO updatedStudentDTO) {
        // Pass the ID and updated student DTO to the service method for updating
        String message = studentService.updateStudent(id, updatedStudentDTO);
        return ResponseEntity.ok(message);
    }
}
