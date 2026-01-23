package com.schedule.controller;

import com.schedule.dto.*;
import com.schedule.repository.ScheduleRepository;
import com.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleCreateResponse> create(
            @RequestBody ScheduleCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));

    }

    @GetMapping
    public ResponseEntity<List<ScheduleGetResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleGetResponse> getOne(
            @PathVariable Long id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleUpdateResponse> update(
            @PathVariable Long id,
            @RequestBody ScheduleUpdateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ) {
        scheduleService.delete(id);
    }
}
