package com.ums.Universitymanagementsystem.apis;

import com.ums.Universitymanagementsystem.dto.ExamDTO;
import com.ums.Universitymanagementsystem.entity.Exam;
import com.ums.Universitymanagementsystem.service.CourseService;
import com.ums.Universitymanagementsystem.service.ExamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class ScheduleExamsApi {

    private final ExamService examService;
    private final ModelMapper modelMapper;
    private final CourseService courseService;

    @Autowired
    public ScheduleExamsApi(ExamService examService,
                            ModelMapper modelMapper,
                            CourseService courseService)
    {
        this.examService = examService;
        this.modelMapper = modelMapper;
        this.courseService = courseService;
    }


    public ResponseEntity<String> addExam(ExamDTO examDTO) {
        try{
            Exam exam = modelMapper.map(examDTO, Exam.class);

            // Fetch the course name using the provided course_id
            String courseName = courseService.getCourseNameById(examDTO.getCourse_id());

            // Set the fetched course name to the exam entity
            exam.getCourse().setCourseName(courseName);

            examService.addExam(exam);

            // Construct success message with additional details
            String successMessage = String.format("Exam scheduled successfully:\n Course Name: %s,\n Date: %s,\n Venue: %s,\n Time: %s",
                    exam.getCourse().getCourseName(),
                    exam.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    exam.getExam_venue(),
                    exam.getTime().format(DateTimeFormatter.ofPattern("HH:mm")));

            return ResponseEntity.ok(successMessage);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error scheduling exam: " + e.getMessage());
        }
    }
}
