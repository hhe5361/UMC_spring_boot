package study.demo.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import study.demo.validation.validator.RestaurantExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RestaurantExistValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistRestaurant {
    String message() default "restaurant is not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}