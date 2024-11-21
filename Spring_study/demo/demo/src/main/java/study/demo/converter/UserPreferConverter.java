package study.demo.converter;

import study.demo.domain.FoodCategory;
import study.demo.domain.mapping.UserPreferCategory;

import java.util.List;
import java.util.stream.Collectors;

public class UserPreferConverter {
    public static List<UserPreferCategory> toUserPreferList(List<FoodCategory> foodCategories) {
        return foodCategories.stream().map(foodCategory ->
                UserPreferCategory.builder()
                        .foodCategory(foodCategory)
                        .build()//setter 설정해주면 build에서 알아서 제외해주는 구나
        ).collect(Collectors.toList());
    }
}
