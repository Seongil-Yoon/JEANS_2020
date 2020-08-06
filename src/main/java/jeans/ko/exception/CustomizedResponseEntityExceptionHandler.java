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
@ControllerAdvice //모든 컨트롤러가 실행 될때 이 어노테이션 을 갖고 있는 bean이 실행됨
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
//ResponseEntityExceptionHandler @ExceptionHandler내부  예외를 처리 하는 메소드를 제공

    //모든 오류가 발생 되면 얘가 실행. 얘는 모든 일반적인 오류를 담당
    //서버 오류 status 보내줌   500 번에러 보내줌
    // final 로 선언 한 이유는 모든 예외 처리를 여기서 해서 더이상 하위 클래스 를  파생 해서
    // override 되지 않게 하기 위해서 함
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handelAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //LookNotFoundException 오류 처리하는곳 404 에러 보내줌
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object>handleNotFoundException(Exception ex,WebRequest request){
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    //UnauthorizedException 오류 처리 하는곳  401 에러 보내줌
    @ExceptionHandler(UnauthorizedException.class)
    public final ResponseEntity<Object>UnauthorizedException(Exception ex,WebRequest request){
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    //BadRequestException 오류 처리 하는곳  400 에러 보내줌
    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<Object>BadRequestException(Exception ex,WebRequest request){
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
