package study.demo.converter;

import lombok.Builder;
import org.springframework.data.domain.Page;
import study.demo.domain.User;
import study.demo.domain.enums.MissionStatus;
import study.demo.domain.mapping.Mission;
import study.demo.domain.mapping.UserMission;
import study.demo.web.dto.MissionResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

public class UserMissionConverter {

    public static UserMission AddMissiontoUserMission(User user, Mission mission){
        return UserMission.builder()
                .user(user)
                .mission(mission)
                .status(MissionStatus.progress)
                .build();
    }
    //Mission , status -> response of mission list
    public static MissionResponse.UserMissionDTO UserMissionToMissionResponse(UserMission userMission){
        return MissionResponse.UserMissionDTO.builder()
                .id(userMission.getMission().getId())
                .goal(userMission.getMission().getGoal())
                .missionStatus(userMission.getStatus())
                .goal(userMission.getMission().getGoal())
                .restaurantId(userMission.getMission().getRestaurant().getId())
                .restaurantName(userMission.getMission().getRestaurant().getName())
                .build();
    }
    public static MissionResponse.UserMissionListDtO UserMissionToMissionListResponse(Page<UserMission> userMissions){
        List<MissionResponse.UserMissionDTO> missionList = userMissions.stream().map(UserMissionConverter::UserMissionToMissionResponse).toList();
        return MissionResponse.UserMissionListDtO.builder()
                        .missions(missionList)
                                .createdAt(LocalDateTime.now()).build();
    }
}
