package study.demo.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import study.demo.apiPayload.code.BaseErrorCode;
import study.demo.apiPayload.code.ErrorReasonDTO;


@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"COMMMON500","server error "),
    _BAD_REQUEST(HttpStatus.INTERNAL_SERVER_ERROR,"COMMNON400","INCORRECT REQUEST IS OCCURED"),
    _UNQUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401","require auth" ),
    _FORBIDDEN(HttpStatus.FORBIDDEN,"COMMON403","forbidden request is occured."),

    //related with member error
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST,"MEMBER4001","NO USER EXISTED"),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST,"MEMBER4002" , "NICKNAME IS REQUIRED"),
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST,"FOOD4001","NO FOOD category found"),
    REGISON_NOT_FOUND(HttpStatus.BAD_REQUEST,"REST4001","NO REGION found"),
    RESTAURANT_NOT_FOUND(HttpStatus.BAD_REQUEST,"REST4002","NO RESTUARANT_FOUND"),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST,"USER4001","NO USER FOUND");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason(){
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus(){
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}


