package study.demo.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.jdbc.Expectation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import study.demo.Service.UserCommandService.UserCommandService;
import study.demo.apiPayload.ApiResponse;
import study.demo.converter.ReviewConverter;
import study.demo.converter.UserConverter;
import study.demo.converter.UserMissionConverter;
import study.demo.domain.User;
import study.demo.domain.enums.MissionStatus;
import study.demo.domain.mapping.Review;
import study.demo.domain.mapping.UserMission;
import study.demo.validation.annotation.ExistUser;
import study.demo.validation.annotation.ValidPageIndex;
import study.demo.web.dto.MissionResponse;
import study.demo.web.dto.ReviewResponseDTO;
import study.demo.web.dto.UserRequestDTO;
import study.demo.web.dto.UserResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {
    private final UserCommandService userCommandService;

    @PostMapping("/")
    public ApiResponse<UserResponseDTO.JoinResultDTO> join(@RequestBody @Valid UserRequestDTO.JoinDto request){
        User user = userCommandService.joinUser(request);
        return ApiResponse.onSuccess(UserConverter.toJoinResultDTO(user)); //user qkerh requset converter ehfflsms smRla/
    }

    //add mission list if you want to update user mission status, use put http method
    //should i use DTO ? in that case? very simple case. just get Userid and Mission id
    @PostMapping("/{userId}/mission")
    public ApiResponse<UserResponseDTO.AddMissionResultDTO> addMission(@PathVariable("userId") Long userId, @Valid @RequestBody UserRequestDTO.AddDTO request){
        User user = userCommandService.addMission(request);
        return ApiResponse.onSuccess(UserConverter.toAddMissionResultDTO(user));
    }

    @GetMapping("/{userId}/mission")
    @Operation(summary = "Get List of User's Mission by status parameter", description = "You can Use also Paging. send Pagenumber In form of Query string. And deault status is all of user misisn list, status is boolean ")
    @ApiResponses(
            {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class)))
            }
    )
    @Parameters(
            {
                    @Parameter(name="userId", description = "userid is path variable.")
            }
    )
    //should add querystring
    public ApiResponse<MissionResponse.UserMissionListDtO> getMissionList(@PathVariable("userId") @ExistUser Long userId, @RequestParam(required = true) MissionStatus status , @RequestParam("page") @ValidPageIndex Integer page){
        Page<UserMission> userMission = userCommandService.getUserMissionList(userId,status,page);//Mission 형태로 받아올 거임. status에
        return ApiResponse.onSuccess(UserMissionConverter.UserMissionToMissionListResponse(userMission));
    }

    @GetMapping("/{userId}/review")
    @Operation(summary = "Getting spesific user review list", description = "You can Use also Paging. send Pagenumber In form of Query string")
    @ApiResponses(
            {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class)))
            }
    )
    @Parameters(
            {
                    @Parameter(name="userId", description = "userid is path variable.")
            }
    )
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getUserReviewList(@PathVariable("userId") @ExistUser Long userId, @RequestParam("page") @ValidPageIndex Integer page){
        Page<Review> reviewList = userCommandService.getUserReviewList(userId, page);

        return ApiResponse.onSuccess(ReviewConverter.toReviewPreViewListDTO(reviewList));
    }
}
