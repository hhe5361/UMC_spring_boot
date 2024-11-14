package study.demo.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import study.demo.apiPayload.code.BaseCode;
import study.demo.apiPayload.code.ReasonDTO;

import java.awt.desktop.UserSessionEvent;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    //enum
    _OK(HttpStatus.OK , "COMMON200","성공입니다.");

    private final HttpStatus httpStatus;
    private final  String code;
    private final String message;

    @Override
    public ReasonDTO getReason(){
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build();
    }
}
