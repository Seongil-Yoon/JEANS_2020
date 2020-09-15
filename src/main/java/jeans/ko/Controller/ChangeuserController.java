package jeans.ko.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jeans.ko.Dao.IUserDao;
import jeans.ko.Dto.PasswordDto;
import jeans.ko.Service.IUserService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/userinformation")
public class ChangeuserController {
    private Logger logger = LoggerFactory.getLogger(ChangeuserController.class);

    @Autowired
    HttpSession httpSession;

    @Autowired
    IUserDao userDao;

    @Autowired
    IUserService userService;

    //유저의 비밀번호를 바꾼다.
    @PostMapping(value = "/password")
    public ResponseEntity<String> changePassword(@Valid @RequestBody PasswordDto passwordDto, BindingResult result) throws Exception {
        String id=(String)httpSession.getAttribute("userid");//사용자 id
        String ps=passwordDto.getPs();//비밀번호

        //비밀번호가 정규식에 위배된다.
        if ((result.getFieldError("ps") != null)) {
            System.out.println("Error! = " + result.getFieldError("ps").getDefaultMessage());
            return new ResponseEntity<>("Fail",HttpStatus.BAD_REQUEST);
        }

        //세션의 아이디가 null이다.
        if(id==null){
            logger.info(id);
            return new ResponseEntity<>("Fail",HttpStatus.BAD_REQUEST);
        }

        //마이바티스에 아이디와 비밀번호 전달
        userDao.changePassword(id,ps);

        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
    
    //유저의 프로필사진을 변경
    @PostMapping(value="/profilethumbnail")
    public ResponseEntity<Void> changeThumbnail(@RequestPart("file") MultipartFile picture) throws Exception {
       logger.info("changeThumbnail메소드");
       String id=(String)httpSession.getAttribute("userid");//사용자 id

       //사용자의 프로필 폴더 내 이미지 전체 제거 & 유저테이블에서 picture값을 ""로 변경
       userService.deleteProfilethumbnail(id);

       //사용자의 profile 폴더내 이미지 새로 업로드 & 사용자테이블에 값 업데이트
       userService.setProfilethumbnail(id,picture);
       
       //실패할 조건에 맞춰 에러를 만들자
       
       return new ResponseEntity<>(HttpStatus.OK);
    }
}