package study.demo.web.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.demo.domain.Restaurant;
import study.demo.domain.enums.MissionStatus;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserMissionDTO {
        long id;
        int goal;
        int reward;
        long restaurantId;
        String restaurantName;
        MissionStatus missionStatus;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserMissionListDtO{
        List<UserMissionDTO> missions;
        LocalDateTime createdAt;
    }
}
