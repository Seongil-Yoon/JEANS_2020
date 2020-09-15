package jeans.ko.Service;

import jeans.ko.Dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService {
    //회원가입 : 단 프로필사진은 DB에 이름값만 넣는다.
    public int joinUser(UserDto userDto);

    public UserDto userLogin(UserDto userDto);
    public String getPicture(String userid);

    //유저의 profile 폴더 내 썸네일 전체제거. 그후 유저테이블의 picture값을 공백으로
    public void deleteProfilethumbnail(String userid);

    //유저의 profile 폴더 내 새 썸네일 이미지 업로드. 그 후 유저테이블 picture값을 파일명으로
    public void setProfilethumbnail(String userid, MultipartFile file) throws Exception;
}