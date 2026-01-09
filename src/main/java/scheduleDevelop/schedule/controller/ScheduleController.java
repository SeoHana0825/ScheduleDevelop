package scheduleDevelop.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scheduleDevelop.schedule.dto.*;
import scheduleDevelop.schedule.service.ScheduleService;
import scheduleDevelop.user.dto.SessionUser;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성 - 작성 유저명, 할일 제목, 할일 내용, 작성일, 수정일
    @PostMapping("/schedules")
    public ResponseEntity<ScheduleCreateResponse> create(
            // 로그인을 해야만 일정 생성될 수 있게
            @SessionAttribute(name = "loginUser", required = true) SessionUser sessionUser,
            @RequestBody ScheduleCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

    // 전체 조회
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleGetResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll());
    }

    // 단건 조회 - 단건 정보 조회, 고유 식별자(ID) 사용
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleGetResponse> getOne(
            @PathVariable Long scheduleId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findOne(scheduleId));
    }

    // 일정 수정
    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResponse> update(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleUpdateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(scheduleId, request));
    }

    // 일정 삭제
    @DeleteMapping("/schedules/{scheduleId}")
    public void delete(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleDeleteRequest request
    ) {
        scheduleService.delete(scheduleId, request.getPassword());
    }

}
