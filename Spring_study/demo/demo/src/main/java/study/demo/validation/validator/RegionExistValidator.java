package study.demo.validation.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.demo.Repository.RegionRepository;
import study.demo.apiPayload.code.status.ErrorStatus;
import study.demo.validation.annotation.ExistCategories;
import study.demo.validation.annotation.ExistRegion;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RegionExistValidator implements ConstraintValidator<ExistRegion, Long> {
    private final RegionRepository regionRepository;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        boolean is_exist =  regionRepository.existsById(id);
        if(is_exist){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(String.valueOf(ErrorStatus.REGISON_NOT_FOUND)).addConstraintViolation();
        }
        return is_exist;
    }

    @Override
    public void initialize(ExistRegion existRegion) {
        ConstraintValidator.super.initialize(existRegion);
    }
}
