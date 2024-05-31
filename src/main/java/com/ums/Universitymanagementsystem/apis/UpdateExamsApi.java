package com.ums.Universitymanagementsystem.apis;

import com.ums.Universitymanagementsystem.dto.ExamDTO;
import com.ums.Universitymanagementsystem.entity.Exam;
import com.ums.Universitymanagementsystem.service.ExamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UpdateExamsApi {

    private final ExamService examService;


    @Autowired
    public UpdateExamsApi (ExamService examService)
    {
        this.examService = examService;

    }


    public ResponseEntity<String> updateExam(Integer exam_id, ExamDTO updatedExamDTO) {
        try {
            // Call the service method to update the exam
            examService.updateExam(exam_id, updatedExamDTO);
            return ResponseEntity.ok("Exam with ID " + exam_id + " updated successfully");
        } catch (Exception e) {
            // Return an error response if there's an exception during the update process
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating exam: " + e.getMessage());
        }
    }
}

