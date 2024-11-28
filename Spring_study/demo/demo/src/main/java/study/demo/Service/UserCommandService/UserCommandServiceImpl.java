package study.demo.Service.UserCommandService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import study.demo.Repository.*;
import study.demo.apiPayload.code.status.ErrorStatus;
import study.demo.apiPayload.exception.handler.FoodCategoryHandler;
import study.demo.converter.UserConverter;
import study.demo.converter.UserMissionConverter;
import study.demo.converter.UserPreferConverter;
import study.demo.domain.FoodCategory;
import study.demo.domain.User;
import study.demo.domain.mapping.Mission;
import study.demo.domain.mapping.Review;
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
    private final ReviewRepository reviewRepository;

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
        //experiment. -> In that case of that throw error in Service .
        //get User and Get Mission by each Id.
        //if Mission is already added in to user mission list, then valid annoation is activate.
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid mission ID"));

        //user and service -> UserMission . I think you don't have to do this logic
        UserMission userMission = UserMissionConverter.AddMissiontoUserMission(user,mission);
        userMissionRepository.save(userMission);
        //question . If i saved Usermission in Db, Then user entity that has usermission list also update?
        return user;
    }

    @Override
    public Page<Review> getUserReviewList(Long userId, Integer page) {
        //get user And review list
        User user = userRepository.findById(userId).get();

        //Get review List
        Page<Review> review = reviewRepository.findAllByUser(user, PageRequest.of(page, 10));
        return review;
    }
}

//user object를 만드는 작업를 서비스? service에서 만들지 converter에서 만들지도 정해야 한다.
