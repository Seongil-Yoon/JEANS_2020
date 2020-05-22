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


}
