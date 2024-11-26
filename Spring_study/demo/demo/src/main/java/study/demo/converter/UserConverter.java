package study.demo.converter;

import study.demo.domain.User;
import study.demo.domain.enums.Gender;
import study.demo.web.dto.UserRequestDTO;
import study.demo.web.dto.UserResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserConverter {

    //responsedto로 반환해주는 역할. user 입력받고 response 필요한 데이터로 반환하는 거임. *-.>* name filed name
    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(User user) {
        return UserResponseDTO.JoinResultDTO.builder()
                .userId(user.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    //requestDTO 받고 user로 바꾸는 converter임.
    public static User toUser(UserRequestDTO.JoinDto request) {
        Gender gender = null;

        switch (request.getGender()) {
            case 1:
                gender = Gender.Male;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return User.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .name(request.getName())
                .age(request.getAge())
                .userPreferList(new ArrayList<>())
                .userAgreeList(new ArrayList<>())
                .point(0)
                .build();
    }

    public static UserResponseDTO.AddMissionResultDTO toAddMissionResultDTO(User user) {
        return UserResponseDTO.AddMissionResultDTO.builder()
                        .userId(user.getId())
                                .updatedAt(LocalDateTime.now()).build();
    }
}

