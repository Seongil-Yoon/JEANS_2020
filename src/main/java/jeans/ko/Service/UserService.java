package jeans.ko.Service;

import jeans.ko.Dao.IUserDao;
import jeans.ko.Dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private UserDto userDto;

    public String joinUser(String id,String pw,String nick,String sex,String height,String weight,String picture,String email){
        if(id)
    }
}
