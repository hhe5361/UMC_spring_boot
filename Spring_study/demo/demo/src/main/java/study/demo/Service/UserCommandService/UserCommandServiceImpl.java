package study.demo.Service.UserCommandService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import study.demo.Repository.FoodCategoryRepository;
import study.demo.Repository.MissionRepository;
import study.demo.Repository.UserMissionRepository;
import study.demo.Repository.UserRepository;
import study.demo.apiPayload.code.status.ErrorStatus;
import study.demo.apiPayload.exception.handler.FoodCategoryHandler;
import study.demo.converter.UserConverter;
import study.demo.converter.UserMissionConverter;
import study.demo.converter.UserPreferConverter;
import study.demo.domain.FoodCategory;
import study.demo.domain.User;
import study.demo.domain.mapping.Mission;
import study.demo.domain.mapping.UserMission;
import study.demo.domain.mapping.UserPreferCategory;
import study.demo.web.dto.UserRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public User joinUser(UserRequestDTO.JoinDto request){
        User newUser = UserConverter.toUser(request); //DTO -> USER converter using
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream() //is food category is validtae?
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).toList();

        List<UserPreferCategory> userPreferList = UserPreferConverter.toUserPreferList(foodCategoryList);
        userPreferList.forEach(items -> {items.SetUser(newUser);});

        return userRepository.save(newUser);
    }

    @Override
    public User addMission(UserRequestDTO.AddDTO request) {
        //요기는 valid 사용 안했어용
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid mission ID"));

        UserMission userMission = UserMissionConverter.AddMissiontoUserMission(request,user,mission);
        userMissionRepository.save(userMission);

        return user;
    }

}

//user object를 만드는 작업를 서비스? service에서 만들지 converter에서 만들지도 정해야 한다.
