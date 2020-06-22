package jeans.ko.exception;

public class UnauthorizedException extends RuntimeException {
    // 올바르지 않은 접근 차단 하는곳
    public UnauthorizedException(String message){
        super(message);
    }

}
