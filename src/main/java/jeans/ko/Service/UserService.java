package jeans.ko.Service;

import jeans.ko.Dao.IUserDao;
import jeans.ko.Dto.UserDto;
import org.apache.jasper.compiler.JspUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService implements IUserService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private IUserDao userDao;

    @Autowired
    private UserDto userDto;


    public int joinUser(UserDto userDto){
        userDto.setRole(1);

        int insert_count= userDao.insertUser(userDto);
        //dao에서 db에 넣는게 성공했는지 안했는지 확인
        //이게 userDao 가 성공했는지 안했는지 확인하고 if 문으로 가랄져야한다
        return insert_count;
    }

    public UserDto userLogin(UserDto userDto){

        UserDto successLogin=null;

        String id=userDto.getUserid();
        String password=userDto.getPassword();

        successLogin=userDao.getInformation(userDto);

        if(successLogin!=null){
            return successLogin;
        }else{
            return successLogin;
        }
    }

    public String getPicture(String userid){

        String picture=userDao.getPicture(userid);
        return picture;
    }


}