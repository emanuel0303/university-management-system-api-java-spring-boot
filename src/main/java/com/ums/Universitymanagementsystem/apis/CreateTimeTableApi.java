package com.ums.Universitymanagementsystem.apis;

import com.ums.Universitymanagementsystem.dto.TimeTableDTO;
import com.ums.Universitymanagementsystem.entity.TimeTable;
import com.ums.Universitymanagementsystem.service.TimeTableService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CreateTimeTableApi {

    private final TimeTableService timeTableService;
    private final ModelMapper modelMapper;


    @Autowired
    public CreateTimeTableApi(TimeTableService timeTableService,
                              ModelMapper modelMapper) {
        this.timeTableService = timeTableService;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<String> createTimeTable(TimeTableDTO timeTableDTO) {

        try {
            // Convert TimeTableDTO to TimeTable entity using ModelMapper
            TimeTable timeTable = modelMapper.map(timeTableDTO, TimeTable.class);

            timeTable.setCourse(timeTableService.findCourseById(timeTableDTO.getCourse_id()));
            timeTable.setFaculty(timeTableService.findFacultyById(timeTableDTO.getFaculty_id()));
            timeTable.setClassroom(timeTableService.findClassroomById(timeTableDTO.getClass_id()));
            // Call the service to save the timetable
            timeTableService.saveTimeTable(timeTable);
            // Create a success message with the additional details
            String successMessage = String.format("Timetable created successfully: Course Name: %s, Faculty Name: %s, Day: %s, Time: %s",
                    timeTable.getCourse().getCourseName(),
                    timeTable.getFaculty().getFacultyName(),
                    timeTable.getDay(),
                    timeTable.getTime().toString());

            return ResponseEntity.ok(successMessage);
        } catch (Exception e) {
            // Handle exceptions and return an appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating timetable: " + e.getMessage());
        }
    }
}

