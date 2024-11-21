package study.demo.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import study.demo.validation.validator.CategoriesExistValidator;
import study.demo.validation.validator.RegionExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RegionExistValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistRegion {
    String message() default "region is not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
