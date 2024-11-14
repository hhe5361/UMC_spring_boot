package study.demo.Service.TempService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.demo.apiPayload.code.status.ErrorStatus;
import study.demo.apiPayload.exception.handler.TempHandler;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TempQueryServicempl implements TempQueryService{
    @Override
    public void CheckFlag(String flag){
        if (Objects.equals(flag, "1")) throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
    }
}
