package scheduleDevelop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scheduleDevelop.dto.ScheduleCreateRequest;
import scheduleDevelop.dto.ScheduleCreateResponse;
import scheduleDevelop.dto.ScheduleGetResponse;
import scheduleDevelop.entity.Schedule;
import scheduleDevelop.repository.Repository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final Repository repository;

    // 1. 일정 생성
    @Transactional
    public ScheduleCreateResponse save (ScheduleCreateRequest request) {
        Schedule schedule = new Schedule(
                request.getUsername(),
                request.getTitle(),
                request.getText()
        );

        Schedule savedSchedule = repository.save(schedule);
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
    public List<ScheduleGetResponse> findAll(String criteria) {

        // criteria에 따라 정렬된 schedule 목록 먼저 가져오기 (내림차순)
        List<Schedule> schedules;
        if ("title".equals(criteria)) {
            schedules = repository.findAllByOrderByTitleDesc();
        } else if ("username".equals(criteria)) {
            schedules = repository.findAllByOrderByUsernameDesc();
        } else {
            schedules = repository.findAllByOrderByUpdatedDateDesc();
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
            ) ;
            dtos.add(dto);
        }
        return dtos;
    }

    // 단건 일정 조회

}
