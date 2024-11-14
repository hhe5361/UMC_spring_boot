package study.demo.converter;

import study.demo.web.dto.TempResponse;

//converter가
public class TempConverter {
    public static TempResponse.TempTestDTO toTempTestDTO(){
        return TempResponse.TempTestDTO.builder()
                .testString("test용")
                .build();
    }

    public static TempResponse.TempExceptionDTO toTempExceptionDTO(String flag){
        return TempResponse.TempExceptionDTO.builder()
                .flag(flag)
                .build();
    }
}
