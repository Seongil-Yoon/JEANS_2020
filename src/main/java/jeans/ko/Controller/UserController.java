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

    @PostMapping("/loginRequest")
    public String loginRequest(@Valid UserDto userDto,BindingResult result) {

        String nickname= userService.userLogin(userDto);//닉네임 값을 받아오도록


        if(nickname!=null){//닉네임 값이 널이 아니면 여기로
            httpSession.setAttribute("userid",userDto.getUserid());
            httpSession.setAttribute("usernickname",nickname);
            return "redirect:/main"; //메인 화면 가지않고 게시판 블러오기 위해 main 재요청
        }else{
            return "loginUser";
        }
    }


    @RequestMapping("/logout")
    public String logout(){
        httpSession.invalidate();
        return "redirect:/main";
    }
}
