package com.ums.Universitymanagementsystem.apis;

import com.ums.Universitymanagementsystem.dto.CourseDTO;
import com.ums.Universitymanagementsystem.entity.Course;
import com.ums.Universitymanagementsystem.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddCourseApi {

    private final CourseService courseService;
    private final ModelMapper modelMapper;

    @Autowired
    public AddCourseApi(CourseService courseService, ModelMapper modelMapper) {
        this.courseService = courseService;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<String> addCourse(CourseDTO courseDTO) {
        Course course = modelMapper.map(courseDTO, Course.class);
        // Call the CourseService to add the course
        courseService.addCourse(course);
        String successMessage = "Course added successfully..\n" +
                "Course ID: " + course.getCourse_id() + "\n" +
                "Course Name: " + course.getCourseName() + "\n" +
                "Credits: " + course.getCredits();
        return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
    }
}
