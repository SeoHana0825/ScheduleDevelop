package scheduleDevelop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import scheduleDevelop.entity.Schedule;

import java.util.List;

public interface Repository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByOrderByUsernameDesc();
    List<Schedule> findAllByOrderByUpdatedDateDesc();
    List<Schedule> findAllByOrderByTitleDesc();

}
