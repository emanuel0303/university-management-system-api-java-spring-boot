package com.ums.Universitymanagementsystem.apis;

import com.ums.Universitymanagementsystem.exception.CourseNotFoundException;
import com.ums.Universitymanagementsystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DeleteCourseApi {

    private final CourseService courseService;

    @Autowired
    public DeleteCourseApi(CourseService courseService) {
        this.courseService = courseService;
    }

    public ResponseEntity<String> deleteCourse(Integer id) {
        try{
            courseService.deleteCourseById(id);
            return ResponseEntity.ok("Course with ID:" + id + "deleted successfully");

        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found with ID: " + id);
        }

    }
}
