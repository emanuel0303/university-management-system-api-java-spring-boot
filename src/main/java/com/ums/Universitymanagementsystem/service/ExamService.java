package com.ums.Universitymanagementsystem.service;

import com.ums.Universitymanagementsystem.dto.ExamDTO;
import com.ums.Universitymanagementsystem.entity.Course;
import com.ums.Universitymanagementsystem.entity.Exam;
import com.ums.Universitymanagementsystem.exception.ExamNotFoundException;
import com.ums.Universitymanagementsystem.repository.ExamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExamService {

    private final ExamRepository examRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ExamService(ExamRepository examRepository, ModelMapper modelMapper)
    {
        this.examRepository = examRepository;
         this.modelMapper = modelMapper;
    }


    public void addExam(Exam exam) {
        examRepository.save(exam);
    }

    public void updateExam(Integer exam_id, ExamDTO updatedExamDTO) {
        // Find the exam by ID
        Optional<Exam> optionalExam = examRepository.findById(exam_id);
        if (optionalExam.isPresent()) {
            // Retrieve the existing exam entity
            Exam existingExam = optionalExam.get();

            // Map the updated exam DTO to an entity
            Exam updatedExam = modelMapper.map(updatedExamDTO, Exam.class);

            // Update only non-null and non-empty fields
            if (updatedExam.getDate() != null) {
                existingExam.setDate(updatedExam.getDate());
            }
            if (updatedExam.getExam_venue() != null && !updatedExam.getExam_venue().isEmpty()) {
                existingExam.setExam_venue(updatedExam.getExam_venue());
            }
            if (updatedExam.getCourse() != null) {
                existingExam.setCourse(updatedExam.getCourse());
            }
            // Update other fields similarly...

            // Save the updated exam
            examRepository.save(existingExam);
        } else {
            throw new ExamNotFoundException("Exam not found with ID: " + exam_id);
        }
    }


    public List<ExamDTO> getExamsByCourseId(Integer course_id) {
        // Convert course_id to a Course entity
        Course course = new Course();
        course.setCourse_id(course_id);

        // Retrieve exams by course entity
        List<Exam> exams = examRepository.findByCourse(course);

        // Map exams to DTOs
        return exams.stream()
                .map(exam -> modelMapper.map(exam, ExamDTO.class))
                .collect(Collectors.toList());
    }
}



