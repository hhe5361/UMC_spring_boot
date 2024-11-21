package study.demo.apiPayload.exception.handler;

import study.demo.apiPayload.code.BaseErrorCode;
import study.demo.apiPayload.exception.GeneralException;

public class RegionCategoryHandler extends GeneralException {
    public RegionCategoryHandler(BaseErrorCode code) {
        super(code);
    }
}
