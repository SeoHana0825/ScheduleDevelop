package scheduleDevelop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scheduleDevelop.dto.ScheduleCreateResponse;
import scheduleDevelop.dto.ScheduleGetResponse;
import scheduleDevelop.service.ScheduleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성 - 작성 유저명, 할일 제목, 할일 내용, 작성일, 수정일
    @PostMapping("/schedules")
    public ResponseEntity<ScheduleCreateResponse> create(
            @RequestBody ScheduleCreateResponse request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(request);
    }

    // 전체 조회 - 작성자명 기준으로 목록 조회 (포함 안될수도), 수정일 기준 내림차순 정렬
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleGetResponse>> getAll (
            @RequestParam (required = false, defaultValue = "updatedDate", value = "criteria") String criteria) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll(criteria));
    }

    // 단건 조회 - 단건 정보 조회, 고유 식별자(ID) 사용
    @GetMapping("/schedules/{scheduleId}")
    public



}
