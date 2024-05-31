package com.ums.Universitymanagementsystem.service;

import com.ums.Universitymanagementsystem.entity.Grade;
import com.ums.Universitymanagementsystem.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public void saveGrade(Grade grade) {
        gradeRepository.save(grade);
    }

}
