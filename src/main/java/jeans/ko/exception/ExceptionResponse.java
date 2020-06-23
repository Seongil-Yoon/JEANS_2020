package jeans.ko.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor // 모든 필드를다 가지고 있는 생성자
@NoArgsConstructor  // 매개 변수가 없는 생성자
public class ExceptionResponse {

    private Date timestamp; //예외발생시간
    private String message; //예외 메시지
    private String details; //예외 상세정보

}
