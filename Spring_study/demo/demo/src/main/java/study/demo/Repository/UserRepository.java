package study.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.demo.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}