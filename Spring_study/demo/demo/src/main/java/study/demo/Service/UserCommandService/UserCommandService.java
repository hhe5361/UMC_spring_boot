package study.demo.Service.UserCommandService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.demo.domain.User;
import study.demo.domain.mapping.Review;
import study.demo.web.dto.UserRequestDTO;

public interface UserCommandService {
    User joinUser(UserRequestDTO.JoinDto request);
    User addMission(UserRequestDTO.AddDTO request);
    Page<Review> getUserReviewList(Long userId,Integer page);
}
