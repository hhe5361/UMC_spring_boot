package study.demo.apiPayload.exception.handler;

import study.demo.apiPayload.code.BaseErrorCode;
import study.demo.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode){
        super(errorCode);
    }
}
