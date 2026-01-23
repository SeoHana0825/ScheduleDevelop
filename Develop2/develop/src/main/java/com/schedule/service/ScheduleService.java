package com.schedule.service;

import com.schedule.dto.*;
import com.schedule.entity.Schedule;
import com.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleCreateResponse save(ScheduleCreateRequest request) {
        Schedule schedule = new Schedule(
                request.getUserName(),
                request.getTitle(),
                request.getContent()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleCreateResponse(
                savedSchedule.getId(),
                savedSchedule.getUserName(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getUpdatedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<ScheduleGetResponse> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(schedule -> new ScheduleGetResponse(
                        schedule.getId(),
                        schedule.getUserName(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getCreatedAt(),
                        schedule.getUpdatedAt()
                )).toList();
    }

    @Transactional(readOnly = true)
    public ScheduleGetResponse findOne(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("해당 일정이 없습니다.")
        );
        return new ScheduleGetResponse(
                schedule.getId(),
                schedule.getUserName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    @Transactional
    public ScheduleUpdateResponse update(Long id, ScheduleUpdateRequest request) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("해당 일정이 없습니다.")
        );
        schedule.update(
                request.getTitle(),
                request.getContent()
        );
        return new ScheduleUpdateResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    @Transactional
    public void delete(Long id) {
        // 존재 하기만 물어보기 때문에 참/거짓으로만 찾음 (비밀번호 없을때)
        boolean existence = scheduleRepository.existsById(id); // exists : 너 존재하니?? 만 물어봄
        if(!existence) {
            throw new IllegalStateException("해당 일정이 없습니다.");
        }
        scheduleRepository.deleteById(id);
    }
}
