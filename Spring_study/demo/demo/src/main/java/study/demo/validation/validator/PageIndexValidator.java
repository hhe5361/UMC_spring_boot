package study.demo.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import study.demo.validation.annotation.ValidPageIndex;

public class PageIndexValidator implements ConstraintValidator<ValidPageIndex,Integer> {
    @Override
    public boolean isValid(Integer index, ConstraintValidatorContext constraintValidatorContext) {

        return index >= 1 ;
    }

}
