package study.demo.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.demo.Service.UserCommandService.UserCommandService;
import study.demo.apiPayload.ApiResponse;
import study.demo.converter.UserConverter;
import study.demo.domain.User;
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

    @PostMapping("/{userId}/mission")
    public ApiResponse<UserResponseDTO.AddMissionResultDTO> addMission(@PathVariable("userId") Long userId, @Valid @RequestBody UserRequestDTO.AddDTO request){
        User user = userCommandService.addMission(request);
        return ApiResponse.onSuccess(UserConverter.toAddMissionResultDTO(user));
    }
}
