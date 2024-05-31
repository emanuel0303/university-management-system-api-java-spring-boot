package com.ums.Universitymanagementsystem.apis;

import com.ums.Universitymanagementsystem.dto.DepartmentDTO;
import com.ums.Universitymanagementsystem.entity.Department;
import com.ums.Universitymanagementsystem.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AddDeptApi {

    private final DepartmentService departmentService;
    private final ModelMapper modelMapper;

    @Autowired
    public AddDeptApi(DepartmentService departmentService,
                      ModelMapper modelMapper)
    {
        this.departmentService = departmentService;
        this.modelMapper = modelMapper;
    }


    public ResponseEntity<String> addDept(DepartmentDTO departmentDTO) {
        Department department = modelMapper.map(departmentDTO, Department.class);
        // Call the DepartmentService to add the course
        departmentService.addDept(department);
        String successMessage = "Department created successfully..\n" +
                "Department ID: " + department.getDept_id() + "\n" +
                "Department Name: " + department.getDeptName() + "\n" ;
        return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
    }
}

