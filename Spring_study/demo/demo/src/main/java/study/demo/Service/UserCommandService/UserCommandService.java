package study.demo.Service.UserCommandService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.demo.domain.User;
import study.demo.domain.enums.MissionStatus;
import study.demo.domain.mapping.Mission;
import study.demo.domain.mapping.Review;
import study.demo.domain.mapping.UserMission;
import study.demo.web.dto.UserRequestDTO;

public interface UserCommandService {
    User joinUser(UserRequestDTO.JoinDto request);
    User addMission(UserRequestDTO.AddDTO request);
    Page<Review> getUserReviewList(Long userId,Integer page);
    Page<UserMission> getUserMissionList(Long userId, MissionStatus missionStatus,Integer page);
}
