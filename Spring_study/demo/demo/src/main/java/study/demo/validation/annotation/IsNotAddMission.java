package study.demo.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import study.demo.validation.validator.IsNotAddMissionValidator;
import study.demo.validation.validator.RestaurantExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsNotAddMissionValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsNotAddMission {
    String message() default "Mission can't add";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
