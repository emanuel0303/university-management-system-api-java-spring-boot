package com.ums.Universitymanagementsystem.apis;

import com.ums.Universitymanagementsystem.dto.CourseDTO;
import com.ums.Universitymanagementsystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class UpdateCourseApi {

    private final CourseService courseService;

    @Autowired
    public UpdateCourseApi(CourseService courseService) {
        this.courseService = courseService;
    }

    public ResponseEntity<String> updateCourse(Integer id, CourseDTO updatedCourseDTO) {
        // Pass the ID and updated course DTO to the service method for updating
        String message = courseService.updateCourse(id, updatedCourseDTO);
        return ResponseEntity.ok(message);
    }
}

