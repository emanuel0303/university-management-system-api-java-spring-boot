package com.ums.Universitymanagementsystem.apis;

import com.ums.Universitymanagementsystem.dto.CourseDTO;
import com.ums.Universitymanagementsystem.entity.Course;
import com.ums.Universitymanagementsystem.exception.CourseNotFoundException;
import com.ums.Universitymanagementsystem.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class GetCourseApi {

    private final CourseService courseService;
    private final ModelMapper modelMapper;

    @Autowired
    public GetCourseApi(CourseService courseService, ModelMapper modelMapper) {
        this.courseService = courseService;
        this.modelMapper = modelMapper;
    }
    public ResponseEntity<?>getCourse(Integer course_id){
        try{
            Course course = courseService.getCourseById(course_id);
            CourseDTO courseDTO = modelMapper.map(course, CourseDTO.class);
            return ResponseEntity.ok(courseDTO);
        } catch (CourseNotFoundException e){
            return ResponseEntity.status(404).body("ID not found in database");
        }
    }
}
