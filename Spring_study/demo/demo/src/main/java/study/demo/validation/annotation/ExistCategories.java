package study.demo.validation.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import study.demo.validation.validator.CategoriesExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CategoriesExistValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistCategories {
    String message() default "category not found";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
