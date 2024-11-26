package study.demo.converter;

import lombok.Builder;
import study.demo.domain.User;
import study.demo.domain.enums.MissionStatus;
import study.demo.domain.mapping.Mission;
import study.demo.domain.mapping.UserMission;
import study.demo.web.dto.UserRequestDTO;

public class UserMissionConverter {

    public static UserMission AddMissiontoUserMission(UserRequestDTO.AddDTO request, User user, Mission mission){
        return UserMission.builder()
                .user(user)
                .mission(mission)
                .status(MissionStatus.progress)
                .build();
    }
}
