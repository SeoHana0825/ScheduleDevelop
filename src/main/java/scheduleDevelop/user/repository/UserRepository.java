package scheduleDevelop.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import scheduleDevelop.user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
