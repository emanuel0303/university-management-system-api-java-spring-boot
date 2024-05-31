package com.ums.Universitymanagementsystem.service;

import com.ums.Universitymanagementsystem.dto.FacultyDTO;
import com.ums.Universitymanagementsystem.entity.Course;
import com.ums.Universitymanagementsystem.entity.Department;
import com.ums.Universitymanagementsystem.entity.Faculty;
import com.ums.Universitymanagementsystem.exception.CourseNotFoundException;
import com.ums.Universitymanagementsystem.exception.DepartmentNotFoundException;
import com.ums.Universitymanagementsystem.exception.FacultyNotFoundException;
import com.ums.Universitymanagementsystem.repository.CourseRepository;
import com.ums.Universitymanagementsystem.repository.DepartmentRepository;
import com.ums.Universitymanagementsystem.repository.FacultyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final ModelMapper modelMapper;
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository, ModelMapper modelMapper,
                          CourseRepository courseRepository,
                          DepartmentRepository departmentRepository) {
        this.facultyRepository = facultyRepository;
        this.modelMapper = modelMapper;
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;

    }

    // Creates a new Faculty entity from the provided FacultyDTO
    public Faculty createFaculty(FacultyDTO facultyDTO) {
        Faculty faculty = modelMapper.map(facultyDTO, Faculty.class);

        // Retrieve the Department entity using the dept_id from the FacultyDTO
        Department department = departmentRepository.findById(facultyDTO.getDept_id())
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found with ID: " + facultyDTO.getDept_id()));

        // Set the Department entity in the Faculty object
        faculty.setDepartment(department);

        // Save the faculty object
        facultyRepository.save(faculty);

        return faculty;
    }

    public Faculty getFacultyById(Integer faculty_id) {
        return facultyRepository.findById(faculty_id)
                .orElseThrow(() -> new FacultyNotFoundException("Faculty not found with ID: " + faculty_id));
    }

    public String updateFaculty(Integer faculty_id, FacultyDTO updatedFacultyDTO) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(faculty_id);
        if (optionalFaculty.isPresent()) {
            Faculty existingFaculty = optionalFaculty.get();
            Faculty updatedFaculty = modelMapper.map(updatedFacultyDTO, Faculty.class);

            // Update only non-null and non-empty fields
            if (updatedFaculty.getFacultyName() != null && !updatedFaculty.getFacultyName().isEmpty()) {
                existingFaculty.setFacultyName(updatedFaculty.getFacultyName());
            }

            // Update other fields similarly...

            facultyRepository.save(existingFaculty);
            return "Faculty with ID:" + faculty_id + " updated successfully";
        } else {
            throw new FacultyNotFoundException("Faculty not found with ID: " + faculty_id);
        }
    }

    public void deleteFacultyById(Integer faculty_id) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(faculty_id);
        if (optionalFaculty.isPresent()) {
            facultyRepository.deleteById(faculty_id);
        } else {
            throw new FacultyNotFoundException("Faculty not found with ID: " + faculty_id);
        }
    }


    public void assignFacultyToCourse(Integer faculty_id, Integer course_id) {
        // Retrieve the Faculty entity
        Faculty faculty = facultyRepository.findById(faculty_id)
                .orElseThrow(() -> new FacultyNotFoundException("Faculty not found with ID: " + faculty_id));

        // Retrieve the Course entity
        Course course = courseRepository.findById(course_id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with ID: " + course_id));

        // Add the course to the faculty's list of assigned courses
        faculty.getCourses().add(course);

        // Save the updated faculty entity
        facultyRepository.save(faculty);
    }
}
