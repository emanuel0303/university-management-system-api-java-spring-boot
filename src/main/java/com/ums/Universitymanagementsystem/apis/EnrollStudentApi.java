package com.ums.Universitymanagementsystem.apis;

import com.ums.Universitymanagementsystem.dto.EnrollmentDTO;
import com.ums.Universitymanagementsystem.entity.Enrollment;
import com.ums.Universitymanagementsystem.entity.Student;
import com.ums.Universitymanagementsystem.entity.Course;
import com.ums.Universitymanagementsystem.service.CourseService;
import com.ums.Universitymanagementsystem.service.EnrollmentService;
import com.ums.Universitymanagementsystem.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnrollStudentApi {

    private final EnrollmentService enrollmentService;
    private final StudentService studentService;
    private final CourseService courseService;
    private final ModelMapper modelMapper;

    @Autowired
    public EnrollStudentApi(
            EnrollmentService enrollmentService,
            StudentService studentService,
            CourseService courseService,
            ModelMapper modelMapper) {

        this.enrollmentService = enrollmentService;
        this.studentService = studentService;
        this.courseService = courseService;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<String> enrollStudent(EnrollmentDTO enrollmentDTO) {
        try {
            // Validate student existence
            Student student = studentService.findStudentById(enrollmentDTO.getStud_id());
            if (student == null) {
                return ResponseEntity.badRequest().body("Invalid student ID");
            }

            // Validate course existence
            Course course = courseService.findCourseById(enrollmentDTO.getCourse_id());
            if (course == null) {
                return ResponseEntity.badRequest().body("Invalid course ID");
            }

            // Map DTO to entity
            Enrollment enrollment = modelMapper.map(enrollmentDTO, Enrollment.class);
            enrollment.setStudent(student);
            enrollment.setCourse(course);

            // Save enrollment
            enrollmentService.saveEnrollment(enrollment);

            return ResponseEntity.ok("Student enrolled successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred during enrollment");
        }
    }
}
