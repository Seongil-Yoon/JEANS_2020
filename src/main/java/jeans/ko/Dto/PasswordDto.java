package jeans.ko.Dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Pattern;

@Data
@Component
public class PasswordDto {
    @Pattern(regexp="^(?=.*[!@#$%^&*()?])[0-9ㄱ-힣a-zA-Z!@#$%^&*()?]{8,12}$",message="비밀번호는 8~10자리이며, 영어 대문자,소문자,숫자,특수문자를 하나 이상 적용시켜야합니다.")
    String ps;
}
