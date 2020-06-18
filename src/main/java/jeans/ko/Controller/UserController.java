package jeans.ko.Controller;

import jeans.ko.Dao.IUserDao;
import jeans.ko.Dto.UserDto;
import jeans.ko.Service.IUserService;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

//로그인이
@Controller
public class UserController {
    @Autowired
    IUserService userService;

    @Autowired
    HttpSession httpSession;

    @RequestMapping("/joinUser")
    public String joinUser()
    { return "joinUser"; }

    @PostMapping("/joinRequest")
    public String joinRequest(@Valid UserDto userDto,BindingResult result){

        if(result.hasErrors()){
            System.out.println("에러에 들어왔따");
            System.out.println("result.getErrorCount() = " + result.getErrorCount());
            System.out.println("result.hasGlobalErrors(); = " + result.getFieldError());
            if(result.getFieldError("userid")!=null){
                System.out.println("Error! " + result.getFieldError("userid").getDefaultMessage());
            }
            if(result.getFieldError("nickname")!=null){
                System.out.println("Error! = " + result.getFieldError("nickname").getDefaultMessage());
            }
            if(result.getFieldError("password")!=null){
                System.out.println("Error! = " + result.getFieldError("password").getDefaultMessage());
            }
            if(result.getFieldError("sex")!=null){
                System.out.println("Error! = " + result.getFieldError("sex").getDefaultMessage());
            }
            if(result.getFieldError("email")!=null){
                System.out.println("Error! = " + result.getFieldError("email").getDefaultMessage());
            }
            return "joinUser";
          // return "redirect:/main";
        }

        int check=userService.joinUser(userDto);
        if(check>0)
            return "loginUser";
        else
            return "joinUser";
        //userService 가 정상적으로 작동하면 loginU
    }

    @ResponseBody
    @PostMapping("/session")
    public HashMap<String, Object> loginRequest(UserDto userDto) {

        HashMap<String, Object> map = new HashMap<String, Object>();
        String nickname= userService.userLogin(userDto);//닉네임 값을 받아오도록

        httpSession.setAttribute("userid",userDto.getUserid());
        httpSession.setAttribute("usernickname",nickname);

        map.put("userid",httpSession.getAttribute("userid"));
        map.put("usernickname",httpSession.getAttribute("usernickname"));

        return map; //session 아이디 닉네임 넘겨주기
    }

    @DeleteMapping("/session")
    public void logout(){
        //session 아이디  삭제
        httpSession.invalidate();
    }

}
