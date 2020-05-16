package jeans.ko.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Component
public class UserDto {

    int role;
    @NotNull(message="ID가 널값입니다.")
    @NotEmpty(message="ID가 비어 있습니다.")
    String userid;
    String nickname;
    String password;
    int sex;
    int height;
    int weight;
    String picture;
    String email;
}
