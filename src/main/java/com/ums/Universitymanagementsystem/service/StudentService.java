package com.ums.Universitymanagementsystem.service;

import com.ums.Universitymanagementsystem.dto.StudentDTO;
import com.ums.Universitymanagementsystem.entity.Student;
import com.ums.Universitymanagementsystem.exception.StudentNotFoundException;
import com.ums.Universitymanagementsystem.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public StudentService(StudentRepository studentRepository,
                          ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    public void createStudent(Student student) {
        // Save the student entity in the database
        studentRepository.save(student);
    }




    public Student getStudentById(Integer stud_id) {
        Optional<Student> studentOptional = studentRepository.findById(stud_id);
        if (studentOptional.isPresent()) {
            return studentOptional.get();
        } else {
            throw new StudentNotFoundException("ID not found in database");
        }
    }



    public String updateStudent(Integer stud_id, StudentDTO updatedStudentDTO) {
        // Find the student by ID
        Optional<Student> optionalStudent = studentRepository.findById(stud_id);
        if (optionalStudent.isPresent()) {
            // Map the updated student DTO to an entity
            Student existingStudent = optionalStudent.get();
            Student updatedStudent = modelMapper.map(updatedStudentDTO, Student.class);

            // Update only non-null and non-empty fields
            if (updatedStudent.getFirstName()!= null && !updatedStudent.getFirstName().isEmpty()) {
                existingStudent.setFirstName(updatedStudent.getFirstName());
            }
            if (updatedStudent.getAge() != null && !updatedStudent.getAge().isEmpty()) {
                existingStudent.setAge(updatedStudent.getAge());
            }
            // Update other fields similarly...

            // Save the updated student
            studentRepository.save(existingStudent);

            return "Student with ID:" + stud_id + "updated successfully";
        } else {
            throw new StudentNotFoundException("Student not found with ID: " + stud_id);
        }
    }

    public void deleteStudentById(Integer stud_id) {
        Optional<Student> optionalStudent = studentRepository.findById(stud_id);
        if (optionalStudent.isPresent()) {
            studentRepository.deleteById(stud_id);
        }else {
            throw new StudentNotFoundException("Student not found with ID: " + stud_id);
        }
    }


    public Student findStudentById(Integer stud_id) {
        return studentRepository.findById(stud_id).orElse(null);

    }

}
