package jeans.ko.Service;

import jeans.ko.Dao.IUserDao;
import jeans.ko.Dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private UserDto userDto;


/*
    public List<UserDto> list(){


    }*/


    public int joinUser(UserDto userDto){
        userDto.setRole(1);
        System.out.println("\nuserService에서");
        System.out.println("userDto.getUserid() = " + userDto.getUserid());
        System.out.println("userDto.getEmail() = " + userDto.getEmail());
        System.out.println("userDto.getNickname = " + userDto.getNickname());
        System.out.println("userDto.getPassword() = " + userDto.getPassword());
        System.out.println("userDto.getPicture() = " + userDto.getPicture());
        System.out.println("userDto.getHeight() = " + userDto.getHeight());
        System.out.println("userDto.getWeight() = " + userDto.getWeight());
        System.out.println("userDto.getRole() = " + userDto.getRole());
        System.out.println("userDto.getSex() = " + userDto.getSex());
        System.out.println("userDto = " + userDto);
        int insert_count= userDao.insertUser(userDto);
        System.out.println("insert_count = " + insert_count);
        //dao에서 db에 넣는게 성공했는지 안했는지 확인
        //이게 userDao 가 성공했는지 안했는지 확인하고 if 문으로 가랄져야한다
        return insert_count;
    }

    public String userLogin(UserDto userDto){

        String id=userDto.getUserid();
        String password=userDto.getPassword();
        String nickname=userDto.getNickname();

       String sqlPassword=userDao.userLogin(userDto);
        System.out.println("id password" + id+ password);
       if(sqlPassword.equals(password)){
           System.out.println("로그인성공");
           return "success";
       }else{
           System.out.println("로그인실패");
           return "false";
       }
    }

}
