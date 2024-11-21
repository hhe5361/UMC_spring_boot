package study.demo.Service.UserCommandService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.demo.Repository.FoodCategoryRepository;
import study.demo.Repository.UserRepository;
import study.demo.apiPayload.code.status.ErrorStatus;
import study.demo.apiPayload.exception.handler.FoodCategoryHandler;
import study.demo.converter.UserConverter;
import study.demo.converter.UserPreferConverter;
import study.demo.domain.FoodCategory;
import study.demo.domain.User;
import study.demo.domain.mapping.UserPreferCategory;
import study.demo.web.dto.UserRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final FoodCategoryRepository foodCategoryRepository;

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

}

//user object를 만드는 작업를 서비스? service에서 만들지 converter에서 만들지도 정해야 한다.
