package jeans.ko.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    //validation 유효성 검사 에서 걸리면 이 에러를 보내줌
    public BadRequestException(String messge) {
        super(messge);
    }
}
