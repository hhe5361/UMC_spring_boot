package study.demo.validation.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.demo.Repository.RegionRepository;
import study.demo.Repository.UserRepository;
import study.demo.apiPayload.code.status.ErrorStatus;
import study.demo.validation.annotation.ExistRegion;
import study.demo.validation.annotation.ExistUser;

@Component
@RequiredArgsConstructor
public class UserExistValidator implements ConstraintValidator<ExistUser, Long> {
    private final UserRepository userRepository;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        boolean is_exist =  userRepository.existsById(id);
        if(is_exist){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(String.valueOf(ErrorStatus.USER_NOT_FOUND)).addConstraintViolation();
        }
        return is_exist;
    }

    @Override
    public void initialize(ExistUser existUser) {
        ConstraintValidator.super.initialize(existUser);
    }
}
