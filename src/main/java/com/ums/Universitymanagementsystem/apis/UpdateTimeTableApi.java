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
public class UpdateTimeTableApi {

    private final TimeTableService timeTableService;
    private final ModelMapper modelMapper;

    @Autowired
    public UpdateTimeTableApi(TimeTableService timeTableService,
                              ModelMapper modelMapper) {
        this.timeTableService = timeTableService;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<String> updateTimeTable(Integer tt_id, TimeTableDTO timeTableDTO) {
        try {
            // Find the existing timetable by id
            TimeTable existingTimeTable = timeTableService.findTimeTableById(tt_id);

            if (existingTimeTable == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Timetable not found");
            }

            // Map the updated fields from TimeTableDTO to the existing TimeTable entity
            modelMapper.map(timeTableDTO, existingTimeTable);

            // Retrieve and set the course, faculty, and classroom entities from their IDs
            existingTimeTable.setCourse(timeTableService.findCourseById(timeTableDTO.getCourse_id()));
            existingTimeTable.setFaculty(timeTableService.findFacultyById(timeTableDTO.getFaculty_id()));
            existingTimeTable.setClassroom(timeTableService.findClassroomById(timeTableDTO.getClass_id()));

            // Update the timetable in the database
            timeTableService.updateTimeTable(existingTimeTable);

            // Create a success message with the additional details
            String successMessage = String.format("Timetable updated successfully: Course Name: %s, Faculty Name: %s, Day: %s, Time: %s",
                    existingTimeTable.getCourse().getCourseName(),
                    existingTimeTable.getFaculty().getFacultyName(),
                    existingTimeTable.getDay(),
                    existingTimeTable.getTime().toString());

            return ResponseEntity.ok(successMessage);
        } catch (Exception e) {
            // Handle exceptions and return an appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating timetable: " + e.getMessage());
        }
    }



    }

