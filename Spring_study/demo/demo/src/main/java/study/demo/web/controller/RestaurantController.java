package study.demo.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import study.demo.Service.RestaurantService.RestaurantQueryService;
import study.demo.apiPayload.ApiResponse;
import study.demo.converter.RestaurantConverter;
import study.demo.converter.ReviewConverter;
import study.demo.domain.Restaurant;
import study.demo.domain.mapping.Review;
import study.demo.validation.annotation.ExistRestaurant;
import study.demo.validation.annotation.ValidPageIndex;
import study.demo.web.dto.RestaurantResponseDTO;
import study.demo.web.dto.RestaurantRequestDTO;
import study.demo.web.dto.ReviewRequestDTO;
import study.demo.web.dto.ReviewResponseDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantQueryService restaurantQueryService;

    @PostMapping("")
    public ApiResponse<RestaurantResponseDTO.RegisterDTO> createRestaurant(@RequestBody @Valid RestaurantRequestDTO.RegisterDTO request) {
        Restaurant newrestaurant = restaurantQueryService.Register(request);

        return ApiResponse.onSuccess(RestaurantConverter.toRegisterResponseDTO(newrestaurant));
    }

    @PostMapping("/{restaurantId}/review")
    public ApiResponse<ReviewResponseDTO.RegisterDTO> createReview(@PathVariable("restaurantId") Long restaurantId, @RequestBody @Valid ReviewRequestDTO.WriteDTO request){
        //get review data and put db request by save method.
        Review newreview = restaurantQueryService.addReview(request);

        return ApiResponse.onSuccess(ReviewConverter.toRegisterResponseDto(newreview));
    }

    @GetMapping("/{restaurantId}/review")
    @Operation(summary = "Getting spesific restaurant review list", description = "You can Use also Paging. send Pagenumber In form of Query string")
    @ApiResponses(
            {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class)))
            }
    )
    @Parameters(
            {
                    @Parameter(name="restaurantId", description = "resutaurant id is path variable.")
            }
    )
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistRestaurant @PathVariable("restaurantId") Long restaurantId, @RequestParam(name="page") @ValidPageIndex Integer page) {
        Page<Review> reviewList = restaurantQueryService.getReviewList(restaurantId,page);
        return ApiResponse.onSuccess(ReviewConverter.toReviewPreViewListDTO(reviewList));
    }

}
