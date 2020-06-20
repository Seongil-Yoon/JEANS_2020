package jeans.ko.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

//Http Status Code
//2XX -> OK 서버측에 요청된 처리가 정상처리됨을 알린다
//4XX -> 클라이언트가 잘못된 요청을 했을때. 권한이 없는것을 요청 혹은 없는 페이지
//5XX -> 서버측에서 오류.

@RestController
@ControllerAdvice //모든컨트롤러가 실행될때 이 어노테이션을 갖고있는 bean이 실행됨
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
//ResponseEntityExceptionHandler @ExceptionHandler내부  예외를 처리 하는 메소드를 제공

    //모든오류가 발생되면 얘가 실행. 얘는 모든 일반적인 오류를 담당
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handelAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
