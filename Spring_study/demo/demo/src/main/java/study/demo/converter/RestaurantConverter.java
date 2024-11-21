package study.demo.converter;

import jakarta.persistence.Converter;
import study.demo.domain.Restaurant;
import study.demo.web.dto.RestaurantResponseDTO;
import study.demo.web.dto.RestaurantRequestDTO;

import java.time.LocalDateTime;

public class RestaurantConverter {
    public static Restaurant toRestaurant(RestaurantRequestDTO.RegisterDTO registerDTO) {
        return Restaurant.builder()
                .name(registerDTO.getName())
                .address(registerDTO.getAddress())
                .latitude(registerDTO.getLatitude())
                .longitude(registerDTO.getLongitude())
                .build();
    }

    //validation will be checked in validator & service
    public static RestaurantResponseDTO.RegisterDTO toRegisterResponseDTO(Restaurant restaurant) {
        return RestaurantResponseDTO.RegisterDTO.builder()
                .restaurantId(restaurant.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
