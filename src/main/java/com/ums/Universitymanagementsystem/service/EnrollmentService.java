package com.ums.Universitymanagementsystem.service;


import com.ums.Universitymanagementsystem.entity.Enrollment;
import com.ums.Universitymanagementsystem.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {


    private final EnrollmentRepository enrollmentRepository;


    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }


    public void saveEnrollment(Enrollment enrollment) {
        enrollmentRepository.save(enrollment);
    }

    public Enrollment findEnrollmentById(Integer enroll_id) {
        return enrollmentRepository.findById(enroll_id).orElse(null);
    }
}
