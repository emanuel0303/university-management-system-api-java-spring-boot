package com.ums.Universitymanagementsystem.controller;


import com.ums.Universitymanagementsystem.entity.Course;
import com.ums.Universitymanagementsystem.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filter")
public class FilterController {


    private final CourseRepository courseRepository;

    @Autowired
    public FilterController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/{courseName}")
    public Course getCrsByCourseName(@PathVariable String courseName) {

        return courseRepository.findByCourseName(courseName);
    }

}
