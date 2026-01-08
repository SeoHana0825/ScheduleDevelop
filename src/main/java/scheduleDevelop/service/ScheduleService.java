package scheduleDevelop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import scheduleDevelop.dto.*;
import scheduleDevelop.entity.Schedule;
import scheduleDevelop.repository.ScheduleRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 1. 일정 생성
    @Transactional
    public ScheduleCreateResponse save(ScheduleCreateRequest request) {
        Schedule schedule = new Schedule(
                request.getUsername(),
                request.getTitle(),
                request.getText(),
                request.getPassword()
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleCreateResponse(
                savedSchedule.getId(),
                savedSchedule.getUsername(),
                savedSchedule.getTitle(),
                savedSchedule.getText(),
                savedSchedule.getCreatedDate(),
                savedSchedule.getUpdatedDate()
        );
    }

    // 전체 일정 조회
    @Transactional(readOnly = true)
    public List<ScheduleGetResponse> findAll(String username) {
        // username에 따라 정렬된 schedule 목록 먼저 가져오기 (내림차순 findAllByOrderByUpdatedDate)
        // username가 있을 수도 있고 없을 수도 있다!!
        List<Schedule> schedules;

        if (username == null) {
            schedules = scheduleRepository.findAllByOrderByUpdatedDateDesc();
        } else {
            // username 가 있는 경우
            schedules = scheduleRepository.findAllByUsernameOrderByUpdatedDateDesc(username);
        }

        List<ScheduleGetResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            ScheduleGetResponse dto = new ScheduleGetResponse(
                    schedule.getId(),
                    schedule.getUsername(),
                    schedule.getTitle(),
                    schedule.getText(),
                    schedule.getCreatedDate(),
                    schedule.getUpdatedDate()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    // 단건 일정 조회
    @Transactional(readOnly = true)
    public ScheduleGetResponse findOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        return new ScheduleGetResponse(
                schedule.getId(),
                schedule.getUsername(),
                schedule.getTitle(),
                schedule.getText(),
                schedule.getCreatedDate(),
                schedule.getUpdatedDate()
        );
    }

    // 일정 수정 (update)
    @Transactional
    public ScheduleUpdateResponse update(Long scheduleId, ScheduleUpdateRequest request) {
        // 일정 조회 없으면 예외
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("일정이 존재하지 않습니다.")
        );

        // 비밀번호가 없을 경우
        if (request.getPassword() == null) {
            throw new IllegalArgumentException("비밀번호를 입력해주세요");
        }

        // 비밀번호가 존재할 경우
        if (request.getPassword().equals(schedule.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        // 수정 가능한 필드만 변경
        schedule.updateTitleAndUsername (
                request.getUsername(),
                request.getTitle()
        );

        return new ScheduleUpdateResponse(
                schedule.getId(),
                schedule.getUsername(),
                schedule.getTitle(),
                schedule.getText(),
                schedule.getCreatedDate(),
                schedule.getUpdatedDate()
        );
    }

    // 일정 삭제
    @Transactional
    public void delete(Long scheduleId, String password) {
        // 일정이 존재하지 않을 때
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("일정이 존재하지 않습니다.")
        );

        // 비밀번호 검증
        if (!schedule.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다. 다시 입력해주세요");
        }

        // 일정이 존재하고, 비밀번호 검증이 통과 했을 때
        scheduleRepository.deleteById(scheduleId);
    }

}
