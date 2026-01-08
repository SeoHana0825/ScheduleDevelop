package scheduleDevelop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scheduleDevelop.dto.*;
import scheduleDevelop.service.ScheduleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성 - 작성 유저명, 할일 제목, 할일 내용, 작성일, 수정일
    @PostMapping("/schedules")
    public ResponseEntity<ScheduleCreateResponse> create(
            @RequestBody ScheduleCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

    // 전체 조회 - 작성자명 기준으로 목록 조회 (포함 안될수도), 수정일 기준 내림차순 정렬
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleGetResponse>> getAll (
            @RequestParam (required = false) String username) {
        // String username 를 넣어주고 셋트인 required =  false 목록 조회에 포함 안될수도 있음
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll(username));
    }

    // 단건 조회 - 단건 정보 조회, 고유 식별자(ID) 사용
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleGetResponse> getOne(
            @PathVariable Long scheduleId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findOne(scheduleId));
    }

    // 일정 수정 - 일정 제목/작성자명만 수정 가능, 비밀번호 전달
    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResponse> update(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleUpdateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(scheduleId, request));
    }

    // 일정 삭제 - 비밀번호 전달
    @DeleteMapping("/schedules/{scheduleId}")
    public void delete(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleDeleteRequest request
    ) {
        scheduleService.delete(scheduleId, request.getPassword());
    }

}
