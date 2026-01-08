package scheduleDevelop.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import scheduleDevelop.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
