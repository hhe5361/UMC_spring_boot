package study.demo.apiPayload.exception.handler;

import study.demo.apiPayload.code.BaseErrorCode;
import study.demo.apiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
