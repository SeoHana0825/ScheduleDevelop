package scheduleDevelop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import scheduleDevelop.entity.Schedule;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByUsernameOrderByUpdatedDateDesc(String username);
    List<Schedule> findAllByOrderByUpdatedDateDesc();


}
