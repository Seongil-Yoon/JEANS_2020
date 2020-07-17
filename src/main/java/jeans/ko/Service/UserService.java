package jeans.ko.Service;

import jeans.ko.Dao.IUserDao;
import jeans.ko.Dto.UserDto;
import org.apache.jasper.compiler.JspUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private UserDto userDto;


    public int joinUser(UserDto userDto){
        userDto.setRole(1);
        System.out.println("nuserService에서");
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
        String sqlPassword=null;
        String id=userDto.getUserid();
        String password=userDto.getPassword();
        String nickname=null;

        sqlPassword=userDao.userLogin(userDto);

        if(sqlPassword==null) {
            System.out.println("회원가입된 아이디가 없으므로 null 값출력");
            return null;   //회원가입된 아이디가 없으므로 null 값줌
        }

       if(sqlPassword.equals(password)){
           System.out.println("로그인성공");
            //여기서 닉네임 받아오면 되지 ㅏㅇㄶ을까
            //String nick=userDao.getNickname(id);
            //로그인 성공시 id 값을 보내고 닉네임을 받아온다.
          nickname=userDao.getNickname(id);
           return nickname;
       }else{
           System.out.println("로그인실패");
           return nickname;
       }
    }

    public String getPicture(String userid){
        String picture=userDao.getPicture(userid);
        return picture;
    }


}
