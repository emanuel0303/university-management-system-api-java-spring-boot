package com.ums.Universitymanagementsystem.apis;

import com.ums.Universitymanagementsystem.dto.ExamDTO;
import com.ums.Universitymanagementsystem.service.ExamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ViewExamApi {

    private final ExamService examService;
    private final ModelMapper modelMapper;

    @Autowired
    public ViewExamApi(ExamService examService, ModelMapper modelMapper) {
        this.examService = examService;
        this.modelMapper = modelMapper;
    }


    public ResponseEntity<?> getExam(Integer course_id) {
        List<ExamDTO> exams = examService.getExamsByCourseId(course_id);

        if (exams.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No exams found for course with ID: " + course_id);
        }

        return ResponseEntity.ok(exams);
    }
}

