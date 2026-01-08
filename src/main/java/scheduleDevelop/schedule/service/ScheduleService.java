package scheduleDevelop.schedule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scheduleDevelop.schedule.dto.*;
import scheduleDevelop.schedule.entity.Schedule;
import scheduleDevelop.schedule.repository.ScheduleRepository;
import scheduleDevelop.user.entity.User;
import scheduleDevelop.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // 1. 일정 생성
    @Transactional
    public ScheduleCreateResponse save(ScheduleCreateRequest request) {
        // request에 담긴 userId로 User 찾기, ScheduleCreateRequest 에 UserId 변수 추가
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalStateException("존재하지 않은 유저입니다.")
        );

        Schedule schedule = new Schedule(
                user,
                request.getTitle(),
                request.getText()
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleCreateResponse(
                savedSchedule.getId(),
                user.getName(),
                savedSchedule.getTitle(),
                savedSchedule.getText(),
                savedSchedule.getCreatedDate(),
                savedSchedule.getUpdatedDate()
        );
    }

    // 전체 일정 조회
    @Transactional(readOnly = true)
    public List<ScheduleGetResponse> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
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
        // 일정 조회 없으면 예외
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

        schedule.update(
                //request.getUsername(),
                request.getTitle(),
                request.getText()
        );

        return new ScheduleUpdateResponse(
                schedule.getId(),
                schedule.getUser().getName(),
                schedule.getTitle(),
                schedule.getText(),
                schedule.getCreatedDate(),
                schedule.getUpdatedDate()
        );
    }

    // 일정 삭제
    @Transactional
    public void delete(Long scheduleId) {
        boolean existence = scheduleRepository.existsById(scheduleId);
        // 일정이 존재하지 않을 경우
        if(!existence) {
            throw new IllegalStateException("일정이 존재하지 않습니다.");
        }
        // 일정 존재할 경우
        scheduleRepository.deleteById(scheduleId);
    }

}
