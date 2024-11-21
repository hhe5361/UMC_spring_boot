package study.demo.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.demo.Service.RestaurantService.RestaurantQueryService;
import study.demo.apiPayload.ApiResponse;
import study.demo.converter.RestaurantConverter;
import study.demo.domain.Restaurant;
import study.demo.web.dto.RestaurantResponseDTO;
import study.demo.web.dto.RestaurantRequestDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantQueryService restaurantQueryService;

    @PostMapping("/")
    private ApiResponse<RestaurantResponseDTO.RegisterDTO> createRestaurant(@RequestBody @Valid RestaurantRequestDTO.RegisterDTO request) {
        Restaurant newrestaurant = restaurantQueryService.Register(request);

        return ApiResponse.onSuccess(RestaurantConverter.toRegisterResponseDTO(newrestaurant));
    }
}
