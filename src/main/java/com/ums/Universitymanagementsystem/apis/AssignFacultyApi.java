package com.ums.Universitymanagementsystem.apis;

import com.ums.Universitymanagementsystem.service.CourseService;
import com.ums.Universitymanagementsystem.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class AssignFacultyApi {
    private final FacultyService facultyService;
    private final CourseService courseService;

    @Autowired
    public AssignFacultyApi(FacultyService facultyService, CourseService courseService) {
        this.facultyService = facultyService;
        this.courseService = courseService;
    }

    public ResponseEntity<String> assignFacultyToCourse(Integer faculty_id, Integer course_id) {
        try {
            // Call the service method to assign faculty to the course
            facultyService.assignFacultyToCourse(faculty_id, course_id);
            return ResponseEntity.ok("Faculty assigned to course successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to assign faculty to course: " + e.getMessage());
        }
    }


}
