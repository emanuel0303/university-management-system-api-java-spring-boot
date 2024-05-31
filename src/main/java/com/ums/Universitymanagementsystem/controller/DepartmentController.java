package com.ums.Universitymanagementsystem.controller;

import com.ums.Universitymanagementsystem.apis.AddDeptApi;
import com.ums.Universitymanagementsystem.dto.DepartmentDTO;
import com.ums.Universitymanagementsystem.dto.ExamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dept")
public class DepartmentController {

     private final AddDeptApi addDeptApi;

     @Autowired
    public DepartmentController(AddDeptApi addDeptApi)
     {
         this.addDeptApi = addDeptApi;
     }

     @PostMapping("/add")
     public ResponseEntity<String> addDept(@Validated @RequestBody DepartmentDTO departmentDTO, BindingResult result) {
         if (result.hasErrors()) {
             // If there are validation errors, construct error message and return bad request response
             String errorMessage = result.getFieldErrors().stream()
                     .map(error -> error.getField() + ": " + error.getDefaultMessage())
                     .findFirst()
                     .orElse("Validation failed");
             return ResponseEntity.badRequest().body(errorMessage);
         }

         return addDeptApi.addDept(departmentDTO);
     }

}
