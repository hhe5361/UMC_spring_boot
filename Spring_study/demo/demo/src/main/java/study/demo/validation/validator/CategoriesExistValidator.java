package study.demo.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.demo.Repository.FoodCategoryRepository;
import study.demo.apiPayload.code.status.ErrorStatus;
import study.demo.validation.annotation.ExistCategories;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoriesExistValidator implements ConstraintValidator<ExistCategories, List<Long>> {

    //repo에 접근 하는 계층은 서비 하인ㅔ 좋ㅡ.
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public boolean isValid(List<Long> longList, ConstraintValidatorContext constraintValidatorContext) {
        boolean isExist = false;
        isExist = longList.stream().allMatch(foodCategoryRepository::existsById);

        if(!isExist) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(String.valueOf(ErrorStatus.FOOD_CATEGORY_NOT_FOUND)).addConstraintViolation();
        }
        return isExist;
    }

    @Override
    public void initialize(ExistCategories existCategories) {
        ConstraintValidator.super.initialize(existCategories);
    }
}
