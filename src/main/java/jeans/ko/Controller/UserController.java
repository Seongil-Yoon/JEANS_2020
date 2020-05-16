package jeans.ko.Controller;

import jeans.ko.Dao.IUserDao;
import jeans.ko.Dto.UserDto;
import jeans.ko.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

//로그인이
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/joinUser")
    public String joinUser()
    { return "joinUser"; }

    @PostMapping("/joinRequest")
    public @ResponseBody String joinRequest(@RequestParam Map<String,String>paraMap){
        String userId=paraMap.get("user_id");
        String userPw=paraMap.get("user_pw");
        String user_Nickname=paraMap.get("user_nickname");
        String user_Sex=paraMap.get("sex");
        String user_Height=paraMap.get("user_height");
        String user_Weight=paraMap.get("user_weight");
        String user_Picture=paraMap.get("user_picture");
        String user_Email=paraMap.get("uwer_email");
       
        userService.joinUser();
    }

}
