package jeans.ko.exception;

import org.springframework.validation.BindingResult;

public class BindingResultException {

    public BindingResultException(int errorCount){
            if(errorCount > 0){
                //유효성 검사 에서 걸려서 에러 보냄
                throw new BadRequestException("validation error");
            }
    }

}
