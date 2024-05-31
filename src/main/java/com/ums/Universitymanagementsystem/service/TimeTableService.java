package com.ums.Universitymanagementsystem.service;

import com.ums.Universitymanagementsystem.entity.Classroom;
import com.ums.Universitymanagementsystem.entity.Course;
import com.ums.Universitymanagementsystem.entity.Faculty;
import com.ums.Universitymanagementsystem.entity.TimeTable;
import com.ums.Universitymanagementsystem.repository.ClassroomRepository;
import com.ums.Universitymanagementsystem.repository.CourseRepository;
import com.ums.Universitymanagementsystem.repository.FacultyRepository;
import com.ums.Universitymanagementsystem.repository.TimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeTableService {

    private final TimeTableRepository timeTableRepository;
    private final CourseRepository courseRepository;
    private final FacultyRepository facultyRepository;
    private final ClassroomRepository classroomRepository;

    @Autowired
    public TimeTableService(TimeTableRepository timeTableRepository,
                            CourseRepository courseRepository,
                            FacultyRepository facultyRepository,
                            ClassroomRepository classroomRepository) {
        this.timeTableRepository = timeTableRepository;
        this.courseRepository = courseRepository;
        this.facultyRepository = facultyRepository;
        this.classroomRepository = classroomRepository;
    }

    public void saveTimeTable(TimeTable timeTable) {
        // Save the entity to the database
        timeTableRepository.save(timeTable);
    }

    public Course findCourseById(Integer course_id) {
        return courseRepository.findById(course_id).orElseThrow( () -> new RuntimeException("Course not found"));
    }

    public Faculty findFacultyById(Integer faculty_id) {
        return facultyRepository.findById(faculty_id).orElseThrow( () -> new RuntimeException("Faculty not found"));
    }

    public Classroom findClassroomById(Integer class_id) {
        return classroomRepository.findById(class_id).orElseThrow( () -> new RuntimeException("Classroom not found"));
    }

    public TimeTable findTimeTableById(Integer tt_id) {
        return timeTableRepository.findById(tt_id).orElse(null);
    }

    public void updateTimeTable(TimeTable timeTable) {
        timeTableRepository.save(timeTable);
    }


}
