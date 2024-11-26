package study.demo.validation.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.demo.Repository.RegionRepository;
import study.demo.Repository.RestaurantRepository;
import study.demo.apiPayload.code.status.ErrorStatus;
import study.demo.validation.annotation.ExistRegion;
import study.demo.validation.annotation.ExistRestaurant;

@Component
@RequiredArgsConstructor
public class RestaurantExistValidator implements ConstraintValidator<ExistRestaurant, Long> {
    private final RestaurantRepository repository;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        boolean is_exist =  repository.existsById(id);
        if(is_exist){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(String.valueOf(ErrorStatus.RESTAURANT_NOT_FOUND)).addConstraintViolation();
        }
        return is_exist;
    }

    @Override
    public void initialize(ExistRestaurant existRestaurant) {
        ConstraintValidator.super.initialize(existRestaurant);
    }
}
