package study.demo.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.demo.Repository.MissionRepository;
import study.demo.Repository.UserMissionRepository;
import study.demo.domain.enums.MissionStatus;
import study.demo.domain.mapping.Mission;
import study.demo.domain.mapping.UserMission;
import study.demo.validation.annotation.IsNotAddMission;
import study.demo.web.dto.UserRequestDTO;

@Component
@RequiredArgsConstructor
public class IsNotAddMissionValidator implements ConstraintValidator<IsNotAddMission, UserRequestDTO.AddDTO> {
    //ㄷ
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    public boolean isValid(UserRequestDTO.AddDTO request, ConstraintValidatorContext context) {
//        if (request == null || request.getMissionId() == null || request.getUserId() == null) {
//            return false;
//        } -> 어차피 request에서 해줄거긴 함.

        //is missin exist?
        Mission mission = missionRepository.findById(request.getMissionId()).orElse(null);
        if(mission == null) {
            context.buildConstraintViolationWithTemplate("Mission is not exist")
                    .addPropertyNode("missionId")
                    .addConstraintViolation();
            return false;
        }

        boolean alreadyAdded = userMissionRepository.existsByUserAndMission(request.getUserId(), request.getMissionId());
        if(alreadyAdded) {
            context.buildConstraintViolationWithTemplate("Mission is already exist")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

}
