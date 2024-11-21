package study.demo.web.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import study.demo.validation.annotation.ExistRestaurant;
import study.demo.validation.annotation.ExistUser;

public class ReviewRequestDTO {

    @Getter
    public static class RegisterDTO{
        @NotBlank
        String content;

        @NotNull
        Integer rating;

        @ExistUser
        @NotNull
        Long user_id;

        @ExistRestaurant
        @NotNull
        Long restaurant_id;
    }
}
