package com.ums.Universitymanagementsystem.controller;

import com.ums.Universitymanagementsystem.apis.CreateTimeTableApi;
import com.ums.Universitymanagementsystem.apis.UpdateTimeTableApi;
import com.ums.Universitymanagementsystem.dto.TimeTableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/timetable")
public class TimetableController {

    private final CreateTimeTableApi createTimeTableApi;
    private final UpdateTimeTableApi updateTimeTableApi;

    @Autowired
    public TimetableController(CreateTimeTableApi createTimeTableApi, UpdateTimeTableApi updateTimeTableApi) {
        this.createTimeTableApi = createTimeTableApi;
        this.updateTimeTableApi = updateTimeTableApi;
    }

    @PostMapping("/add")
    public ResponseEntity<String> createTimetable(@Validated @RequestBody TimeTableDTO timeTableDTO, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .findFirst()
                    .orElse("Validation failed");
            return ResponseEntity.badRequest().body(errorMessage);
        }
        return createTimeTableApi.createTimeTable(timeTableDTO);
    }

    @PutMapping("/update/{tt_id}")
    public ResponseEntity<String> updateTimetable(@PathVariable Integer tt_id, @Validated @RequestBody TimeTableDTO updatedTimeTableDTO) {
        return updateTimeTableApi.updateTimeTable(tt_id, updatedTimeTableDTO);
    }
}
