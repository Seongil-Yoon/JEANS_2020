package jeans.ko.Service;

import jeans.ko.Dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService {
    //회원가입 : 단 프로필사진은 DB에 이름값만 넣는다.
    public int joinUser(UserDto userDto);

    public UserDto userLogin(UserDto userDto);
    public String getPicture(String userid);
}