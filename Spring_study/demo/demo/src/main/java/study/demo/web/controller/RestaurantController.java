package study.demo.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.demo.Service.RestaurantService.RestaurantQueryService;
import study.demo.apiPayload.ApiResponse;
import study.demo.converter.RestaurantConverter;
import study.demo.converter.ReviewConverter;
import study.demo.domain.Restaurant;
import study.demo.domain.mapping.Review;
import study.demo.web.dto.RestaurantResponseDTO;
import study.demo.web.dto.RestaurantRequestDTO;
import study.demo.web.dto.ReviewRequestDTO;
import study.demo.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantQueryService restaurantQueryService;

    @PostMapping("")
    private ApiResponse<RestaurantResponseDTO.RegisterDTO> createRestaurant(@RequestBody @Valid RestaurantRequestDTO.RegisterDTO request) {
        Restaurant newrestaurant = restaurantQueryService.Register(request);

        return ApiResponse.onSuccess(RestaurantConverter.toRegisterResponseDTO(newrestaurant));
    }

    @PostMapping("/{restaurantId}/review")
    private ApiResponse<ReviewResponseDTO.RegisterDTO> createReview(@PathVariable("restaurantId") Long restaurantId, @RequestBody @Valid ReviewRequestDTO.RegisterDTO request){
        Review newreview = restaurantQueryService.addReview(request);

        return ApiResponse.onSuccess(ReviewConverter.toRegisterResponseDto(newreview));
    }
}
