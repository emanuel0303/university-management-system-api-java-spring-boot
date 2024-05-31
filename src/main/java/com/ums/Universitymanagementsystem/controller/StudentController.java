package com.ums.Universitymanagementsystem.controller;

import com.ums.Universitymanagementsystem.apis.*;

import com.ums.Universitymanagementsystem.dto.StudentDTO;
import com.ums.Universitymanagementsystem.repository.StudentRepository;
import com.ums.Universitymanagementsystem.request.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")

public class StudentController {

    private final AddStudentApi addStudentApi;
    private final GetStudentApi getStudentApi;
    private final UpdateStudentApi updateStudentApi;
    private final DeleteStudentApi deleteStudentApi;
    private final StudentRepository studentRepository;


    @Autowired
    public StudentController(AddStudentApi addStudentApi, GetStudentApi getStudentApi,
                             UpdateStudentApi updateStudentApi,
                             DeleteStudentApi deleteStudentApi,
                             StudentRepository studentRepository
                             ) {
        this.addStudentApi = addStudentApi;
        this.getStudentApi = getStudentApi;
        this.updateStudentApi = updateStudentApi;
        this.deleteStudentApi = deleteStudentApi;
        this.studentRepository = studentRepository;

    }

    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@Validated @RequestBody StudentDTO studentDTO, BindingResult result) {
        if (result.hasErrors()) {
            // If there are validation errors, construct error message and return bad request response
            String errorMessage = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .findFirst()
                    .orElse("Validation failed");
            return ResponseEntity.badRequest().body(errorMessage);
        }

        // Proceed with adding the student
        return addStudentApi.addStudent(studentDTO);
    }

    @GetMapping("/get/{stud_id}")
    public ResponseEntity<?> getStudent(@PathVariable Integer stud_id) {
        return getStudentApi.getStudent(stud_id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Integer id,@Validated @RequestBody StudentDTO updatedStudentDTO) {
        return updateStudentApi.updateStudent(id,updatedStudentDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
        return deleteStudentApi.deleteStudent(id);
    }



    @GetMapping("/search")
    public ResponseEntity<List<StudentDTO>> searchStudents(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email) {
        List<StudentDTO> students = studentRepository.findAllBySimpleQuery(firstName, lastName, email);
        return ResponseEntity.ok(students);
    }

    @PostMapping("/search")
    public ResponseEntity<List<StudentDTO>> searchStudentsByCriteria(@RequestBody SearchRequest request) {
        List<StudentDTO> students = studentRepository.findAllByCriteria(request);
        return ResponseEntity.ok(students);
    }
}


