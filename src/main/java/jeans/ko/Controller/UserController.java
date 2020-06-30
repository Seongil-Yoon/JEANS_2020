package jeans.ko.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jeans.ko.Dao.IUserDao;
import jeans.ko.Dto.UserDto;
import jeans.ko.Service.IUserService;
import lombok.extern.flogger.Flogger;
import org.apache.ibatis.io.ResolverUtil;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Binding;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//로그인이
@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IUserService userService;

    @Autowired
    HttpSession httpSession;

    @RequestMapping("/joinUser")
    public String joinUser() {
        return "joinUser";
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/user",params = {"UserDto"})
    public ResponseEntity<Void> join(@RequestPart("UserDto")String userString,BindingResult result) throws IOException {
        logger.info("join()");

        @Valid
        UserDto user = new ObjectMapper().readValue(userString, UserDto.class);

        logger.info(userString);


        System.out.println("result.getErrorCount() = " + result.getErrorCount());
        System.out.println("result.hasGlobalErrors(); = " + result.getFieldError());
        if (result.getFieldError("userid") != null) {
            System.out.println("Error! " + result.getFieldError("userid").getDefaultMessage());
        }
        if (result.getFieldError("nickname") != null) {
            System.out.println("Error! = " + result.getFieldError("nickname").getDefaultMessage());
        }
        if (result.getFieldError("password") != null) {
            System.out.println("Error! = " + result.getFieldError("password").getDefaultMessage());
        }
        if (result.getFieldError("sex") != null) {
            System.out.println("Error! = " + result.getFieldError("sex").getDefaultMessage());
        }
        if (result.getFieldError("email") != null) {
            System.out.println("Error! = " + result.getFieldError("email").getDefaultMessage());
        }
        if(result.getErrorCount()>0){
            System.out.println("이제 자바스크립트로 에러를 보낸다.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //회원가입 이벤트
        int check = userService.joinUser(user);

        //성공적으로 회원가입 시 1반환
        if (check > 0)
            return new ResponseEntity<>(HttpStatus.OK);
        else

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    //REST 형식의 회원가입
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value ="/user" ,params = {"UserDto","file"})
    public ResponseEntity<Void> join(@RequestPart("UserDto") String userString, @RequestPart("file") MultipartFile picture, BindingResult result) throws IOException {
        logger.info("join()");

        @Valid
        UserDto user=new ObjectMapper().readValue(userString,UserDto.class);

        logger.info(userString);
        System.out.println("picture = " + picture);

        System.out.println("result.getErrorCount() = " + result.getErrorCount());
        System.out.println("result.hasGlobalErrors(); = " + result.getFieldError());
        if (result.getFieldError("userid") != null) {
            System.out.println("Error! " + result.getFieldError("userid").getDefaultMessage());
        }
        if (result.getFieldError("nickname") != null) {
            System.out.println("Error! = " + result.getFieldError("nickname").getDefaultMessage());
        }
        if (result.getFieldError("password") != null) {
            System.out.println("Error! = " + result.getFieldError("password").getDefaultMessage());
        }
        if (result.getFieldError("sex") != null) {
            System.out.println("Error! = " + result.getFieldError("sex").getDefaultMessage());
        }
        if (result.getFieldError("email") != null) {
            System.out.println("Error! = " + result.getFieldError("email").getDefaultMessage());
        }
        if(result.getErrorCount()>0){
            System.out.println("이제 자바스크립트로 에러를 보낸다.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //회원가입 이벤트
        int check = userService.joinUser(user);

        //성공적으로 회원가입 시 1반환
        if (check > 0)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/joinRequest")
    public String joinRequest(@Valid UserDto userDto, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println("에러에 들어왔따");
            System.out.println("result.getErrorCount() = " + result.getErrorCount());
            System.out.println("result.hasGlobalErrors(); = " + result.getFieldError());
            if (result.getFieldError("userid") != null) {
                System.out.println("Error! " + result.getFieldError("userid").getDefaultMessage());
            }
            if (result.getFieldError("nickname") != null) {
                System.out.println("Error! = " + result.getFieldError("nickname").getDefaultMessage());
            }
            if (result.getFieldError("password") != null) {
                System.out.println("Error! = " + result.getFieldError("password").getDefaultMessage());
            }
            if (result.getFieldError("sex") != null) {
                System.out.println("Error! = " + result.getFieldError("sex").getDefaultMessage());
            }
            if (result.getFieldError("email") != null) {
                System.out.println("Error! = " + result.getFieldError("email").getDefaultMessage());
            }
            return "joinUser";
            // return "redirect:/main";
        }

        int check = userService.joinUser(userDto);
        if (check > 0)
            return "loginUser";
        else
            return "joinUser";
        //userService 가 정상적으로 작동하면 loginU
    }

    @ResponseBody
    @PostMapping("/session")
    public HashMap<String, Object> loginRequest(UserDto userDto) {

        HashMap<String, Object> map = new HashMap<String, Object>();
        String nickname = userService.userLogin(userDto);//닉네임 값을 받아오도록

        httpSession.setAttribute("userid", userDto.getUserid());
        httpSession.setAttribute("usernickname", nickname);

        map.put("userid", httpSession.getAttribute("userid"));
        map.put("usernickname", httpSession.getAttribute("usernickname"));

        return map; //session 아이디 닉네임 넘겨주기
    }

    @ResponseBody
    @DeleteMapping("/session")
    public void logout() {
        //session 아이디  삭제
        httpSession.invalidate();
    }

}
