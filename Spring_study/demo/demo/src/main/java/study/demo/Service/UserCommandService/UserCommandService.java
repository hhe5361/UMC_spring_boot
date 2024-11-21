package study.demo.Service.UserCommandService;

import study.demo.domain.User;
import study.demo.web.dto.UserRequestDTO;

public interface UserCommandService {
    User joinUser(UserRequestDTO.JoinDto request);
}
