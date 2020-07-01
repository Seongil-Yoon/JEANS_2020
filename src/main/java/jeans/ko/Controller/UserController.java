package jeans.ko.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jeans.ko.Dao.IUserDao;
import jeans.ko.Dto.UserDto;
import jeans.ko.Service.IFileService;
import jeans.ko.Service.IUserService;
import lombok.extern.flogger.Flogger;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.io.ResolverUtil;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Binding;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

//로그인이
@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IUserService userService;

    //회원가입 시 프로필사진을 입력했을 때 사진 업로드를 위한 Service
    @Autowired
    private IFileService fileService;

    //회원가입 시 프로필사진을 입력했을 때 사진 업로드를 위한 경로. application.properties에 등록되어있다.
    @Value("${directory}")
    String uploadPath;

    @Autowired
    HttpSession httpSession;

    @RequestMapping("/joinUser")
    public String joinUser() {
        return "joinUser";
    }


    @ResponseBody
    @PostMapping(value = "/user")
    public ResponseEntity<Void> join(@RequestPart("UserDto")String userString,BindingResult result) throws IOException {
        logger.info("join() 프로필사진이 없다!");

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
    @PostMapping( value ="/userfile" )
    public ResponseEntity<Void> join(@RequestPart("UserDto") String userString, @RequestPart("file") MultipartFile picture, BindingResult result) throws Exception {
        logger.info("join() 프로필사진이 있다.");
        logger.info("파일이름 : "+picture.getOriginalFilename());
        logger.info("파일크기 : "+picture.getSize());
        logger.info("파일컨텐트타입 : "+picture.getContentType());

        @Valid
        UserDto user=new ObjectMapper().readValue(userString,UserDto.class);

        user.setPicture("profile.jpg");
        //우리의 현재 전략은 프로파일 사진은 한사용자당 하나다.
        //사용자가 어떤 이름으로든 프로파일사진을 올리던 우리는 profile.jpg로 저장한다.
        //만약 사용자가 올린 사진명대로 해야된다면 이를 지우고 밑에 주석 된걸 해제해주자.
        //user.setPicture(picture.getOriginalFilename());
        //파일업로드를 담당하는 FileService 부분에도 profile.jpg로 이름을 자동으로 바꿔준다.

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
        boolean test=fileService.uploadProfile(uploadPath,user.getUserid(),picture.getOriginalFilename(),picture.getBytes());
        logger.info("문제시작?");
        fileService.makeThumbnail("profile.jpg",uploadPath,user.getUserid());

        //성공적으로 회원가입 시 1반환
        if (check > 0)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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

    @GetMapping("/display")
    public  ResponseEntity<byte[]> display() throws IOException {
        InputStream in=null;
        ResponseEntity<byte[]>entity=null;
        Object f= httpSession.getAttribute("userid");
        String d=(String)f;
     logger.info(d);
     HttpHeaders headers=new HttpHeaders();
     try {
         in = new FileInputStream(uploadPath + "\\" + d + "\\" + "profile" + "\\profile.jpg");
         headers.setContentType(MediaType.IMAGE_JPEG);
         entity=new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.OK);
     }catch(Exception e){
        entity=new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
     }finally{
         in.close();
     }
        return entity;
    }
}
