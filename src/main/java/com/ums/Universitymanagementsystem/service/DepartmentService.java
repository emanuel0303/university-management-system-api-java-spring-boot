package com.ums.Universitymanagementsystem.service;

import com.ums.Universitymanagementsystem.entity.Department;
import com.ums.Universitymanagementsystem.repository.CourseRepository;
import com.ums.Universitymanagementsystem.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository,
                             ModelMapper modelMapper)
    {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }
    public void addDept(Department department) {
        departmentRepository.save(department);

    }

    public Department findById(Integer dept_id) {
        return departmentRepository.findById(dept_id)
                .orElseThrow(() -> new IllegalArgumentException("Department not found with id: " + dept_id));
    }
}

