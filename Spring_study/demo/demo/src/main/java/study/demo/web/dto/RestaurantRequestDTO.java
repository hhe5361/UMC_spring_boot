package study.demo.web.dto;

import lombok.Getter;
import study.demo.domain.Region;
import study.demo.validation.annotation.ExistRegion;

public class RestaurantRequestDTO {

    @Getter
    public static class RegisterDTO {
        String name;
        String address;
        String latitude;
        String longitude;
        @ExistRegion
        Long region_id;
    }
}