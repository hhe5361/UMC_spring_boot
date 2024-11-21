package study.demo.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import study.demo.domain.Region;
import study.demo.validation.annotation.ExistRegion;

public class RestaurantRequestDTO {

    @Getter
    public static class RegisterDTO {
        @NotBlank
        String name;
        @NotNull
        String address;
        @NotNull
        String latitude;
        @NotNull
        String longitude;
        @ExistRegion
        Long region_id;
    }
}