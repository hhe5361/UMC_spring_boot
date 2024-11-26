package study.demo.Repository;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import study.demo.domain.User;
import study.demo.domain.mapping.Mission;
import study.demo.domain.mapping.UserMission;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    boolean existsByUserAndMission(User user, Mission mission);

    boolean existsByUserAndMission(@NotBlank Long userId, @NotBlank Long missionId);
}
