package com.ums.Universitymanagementsystem.service;

import com.ums.Universitymanagementsystem.dto.CourseDTO;
import com.ums.Universitymanagementsystem.entity.Course;
import com.ums.Universitymanagementsystem.exception.CourseNotFoundException;
import com.ums.Universitymanagementsystem.repository.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CourseService(CourseRepository courseRepository,
                         ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public Course getCourseById(Integer course_id) {
        return courseRepository.findById(course_id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with ID: " + course_id));
    }

    public String updateCourse(Integer course_id, CourseDTO updatedCourseDTO) {
        // Find the course by ID
        Optional<Course> optionalCourse = courseRepository.findById(course_id);
        if (optionalCourse.isPresent()) {
            // Retrieve the existing course entity
            Course existingCourse = optionalCourse.get();

            // Map the updated course DTO to an entity
            Course updatedCourse = modelMapper.map(updatedCourseDTO, Course.class);

            // Update only non-null fields
            if (updatedCourse.getCourseName() != null) {
                existingCourse.setCourseName(updatedCourse.getCourseName());
            }
            if (updatedCourse.getCredits() != null) {
                existingCourse.setCredits(updatedCourse.getCredits());
            }
            if (updatedCourse.getPrerequisites() != null) {
                existingCourse.setPrerequisites(updatedCourse.getPrerequisites());
            }
            // Update other fields similarly...

            // Save the updated course
            courseRepository.save(existingCourse);

            return "Course with ID:" + course_id + " updated successfully";
        } else {
            throw new CourseNotFoundException("Course not found with ID: " + course_id);
        }
    }

    public void deleteCourseById(Integer course_id) {
        Optional<Course> optionalCourse = courseRepository.findById(course_id);
        if (optionalCourse.isPresent()) {
            courseRepository.deleteById(course_id);
        } else {
            throw new CourseNotFoundException("Course not found with ID: " + course_id);
        }

    }

    public String getCourseNameById(Integer course_id) {
        Optional<Course> optionalCourse = courseRepository.findById(course_id);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            return course.getCourseName();
        } else {
            throw new CourseNotFoundException("Course not found with ID: " + course_id);
        }
    }

    public Course findCourseById(Integer course_id) {
        return courseRepository.findById(course_id).orElse(null);

    }


}
