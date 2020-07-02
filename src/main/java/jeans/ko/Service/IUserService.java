package jeans.ko.Service;

import jeans.ko.Dto.UserDto;

public interface IUserService {
    public int joinUser(UserDto userDto);
    public String userLogin(UserDto userDto);
    public String getPicture(String userid);
}
