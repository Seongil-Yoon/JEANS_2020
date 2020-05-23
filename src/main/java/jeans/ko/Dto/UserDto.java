package jeans.ko.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;

@Data
@Component
public class UserDto {

    int role;

    @NotBlank(message="ID에 공백을 허용하지 않습니다")
    @Size(min=4,max=12,message="사용자 아이디는4자리 이상 12자 이하만 가능합니다")
    @Pattern(regexp="^[0-9a-zA-Z]*$" , message="사용자 아이디는 영문자 숫자 조합만 가능 합니다.")
    String userid;

    @NotBlank(message="nickname에 공백을 허용하지 않습니디.")
    @Size(min=4,max=12,message="사용자 닉네임은 4자리 이상 12 자리 이하만 가능합니다")
    @Pattern(regexp="^[0-9a-zA-Z가-힣]*$",message="사용자 닉네임은 영문자 숫자 한글 조합만 가능합니다.")
    String nickname;


    @NotBlank(message="password에 공백을 허용하지 않습니다.")
     @Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()?])[[A-Z][a-z][0-9]!@#$%^&*()?]{8,10}$",message="비밀번호에는 영어 대문자,소문자,숫자,특수문자를 하나 이상 적용시켜야합니다..")
    String password;

    int sex; //0 여자 1 남자

    Integer height;//int로 하면 typeMismatch.int 에러난다. int는 Null값을 null로 저장하는 오류가 없기때문에.
    Integer weight;
    String picture;


    @Email
    @NotBlank(message="email에는 값이 입력되있어야합니다.")
    String email;
}
