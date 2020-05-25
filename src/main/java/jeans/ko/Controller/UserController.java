package jeans.ko.Controller;

import jeans.ko.Dao.IUserDao;
import jeans.ko.Dto.UserDto;
import jeans.ko.Service.UserService;
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
    UserService userService;

    @Autowired
    HttpSession httpSession;

    @RequestMapping("/joinUser")
    public String joinUser()
    { return "joinUser"; }


  /*  @RequestMapping(value = "/testUser", method = RequestMethod.GET)
    public String userlistPage(Model model) {
        // System.out.println("userDao = " + userDao.list());
        model.addAttribute("users", userService.list());
        return "userlist";
    }*/

    @PostMapping("/joinRequest")
    public String joinRequest(@Valid UserDto userDto,BindingResult result){
        //테스트용
   /*     System.out.println("userDto.getUserid() = " + userDto.getUserid());
        System.out.println("userDto.getEmail() = " + userDto.getEmail());
        System.out.println("userDto.getNickname = " + userDto.getNickname());
        System.out.println("userDto.getPassword() = " + userDto.getPassword());
        System.out.println("userDto.getPicture() = " + userDto.getPicture());
        System.out.println("userDto.getHeight() = " + userDto.getHeight());
        System.out.println("userDto.getWeight() = " + userDto.getWeight());
        System.out.println("userDto.getRole() = " + userDto.getRole());
        System.out.println("userDto.getSex() = " + userDto.getSex());*/

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
           return "redirect:/main";
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


        String page= userService.userLogin(userDto);
        if(page.equals("success")){
            System.out.println("userDto.getNickname() = " + userDto.getNickname());
            httpSession.setAttribute("userid",userDto.getUserid());
            httpSession.setAttribute("usernickname",userDto.getNickname());
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
