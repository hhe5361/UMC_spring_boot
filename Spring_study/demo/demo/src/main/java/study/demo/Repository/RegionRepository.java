package study.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.demo.domain.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
