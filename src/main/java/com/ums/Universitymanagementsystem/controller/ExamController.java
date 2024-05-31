package com.ums.Universitymanagementsystem.controller;

import com.ums.Universitymanagementsystem.apis.*;
import com.ums.Universitymanagementsystem.dto.ExamDTO;
import com.ums.Universitymanagementsystem.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exam")
public class ExamController {


    private final ScheduleExamsApi scheduleExamsApi;
    private final UpdateExamsApi updateExamsApi;
    private final ViewExamApi viewExamApi;

    @Autowired
    public ExamController(ScheduleExamsApi scheduleExamsApi,
                          UpdateExamsApi updateExamsApi,
                          ViewExamApi viewExamApi) {
        this.viewExamApi = viewExamApi;
        this.scheduleExamsApi = scheduleExamsApi;
        this.updateExamsApi = updateExamsApi;

    }

    @PostMapping("/add")
    public ResponseEntity<String>addExam(@Validated @RequestBody ExamDTO examDTO,BindingResult result) {
        if (result.hasErrors()) {
            // If there are validation errors, construct error message and return bad request response
            String errorMessage = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .findFirst()
                    .orElse("Validation failed");
            return ResponseEntity.badRequest().body(errorMessage);
        }

        return scheduleExamsApi.addExam(examDTO);
    }

    @PutMapping("/update/{exam_id}")
    public ResponseEntity<String>updateExam(@PathVariable Integer exam_id,@Validated @RequestBody ExamDTO updatedExamDTO)
    {
        return updateExamsApi.updateExam(exam_id, updatedExamDTO);
    }

    @GetMapping("get/{course_id}")
    public ResponseEntity<?>viewExam(@PathVariable Integer course_id)
    {
        return viewExamApi.getExam(course_id);
    }

}
