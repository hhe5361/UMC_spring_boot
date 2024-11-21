package study.demo.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import study.demo.validation.annotation.ExistCategories;

import java.util.List;

public class UserRequestDTO {
    @Getter
    public static class JoinDto {
        @NotBlank
        String name;
        @NotNull
        Integer age;
        @NotNull
        Integer gender;
        @NotNull
        Integer birthYear;
        @NotNull
        Integer birthMonth;
        @NotNull
        Integer birthDay;
        @NotNull
        String address;
        @NotNull
        String specAddress;
        @ExistCategories //ㅕ기 annotation붙이면 컨트롤러서 바 ㄱㅏ아는 가ㅇㅔㅓ 코가 실ㅇㅏㅔㅚ 아 그서 valid가 붙ㅓㅇ구
        List<Long> preferCategory;
    }
}
