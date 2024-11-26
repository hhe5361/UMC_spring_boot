package study.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.demo.domain.mapping.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
