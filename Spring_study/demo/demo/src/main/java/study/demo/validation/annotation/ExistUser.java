package study.demo.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import study.demo.validation.validator.RegionExistValidator;
import study.demo.validation.validator.UserExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserExistValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistUser {
    String message() default "user is not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


