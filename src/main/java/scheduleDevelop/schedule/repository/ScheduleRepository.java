package scheduleDevelop.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import scheduleDevelop.schedule.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
