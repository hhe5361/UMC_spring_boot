package study.demo.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.demo.Repository.MissionRepository;
import study.demo.Repository.UserMissionRepository;
import study.demo.Repository.UserRepository;
import study.demo.apiPayload.code.status.ErrorStatus;
import study.demo.domain.User;
import study.demo.domain.enums.MissionStatus;
import study.demo.domain.mapping.Mission;
import study.demo.domain.mapping.UserMission;
import study.demo.validation.annotation.IsNotAddMission;
import study.demo.web.dto.UserRequestDTO;

@Component
@RequiredArgsConstructor
public class IsNotAddMissionValidator implements ConstraintValidator<IsNotAddMission, UserRequestDTO.AddDTO> {
    //you shold modify here ! you should not call repository here!
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;

    @Override
    public boolean isValid(UserRequestDTO.AddDTO request, ConstraintValidatorContext context) {

        //is missin exist? 사ㄹ service에서 이차 검증  하고  있긴  해ㅐㅐ
        Mission mission = missionRepository.findById(request.getMissionId()).orElse(null);
        if(mission == null) {
            context.buildConstraintViolationWithTemplate("Mission is not exist")
                    .addPropertyNode("missionId")
                    .addConstraintViolation();
            return false;
        }
        User user = userRepository.findById(request.getUserId()).orElse(null);
        boolean alreadyAdded = userMissionRepository.existsByUserAndMission(user,mission);
        if(alreadyAdded) {
            context.buildConstraintViolationWithTemplate("Mission is already exist")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

}
