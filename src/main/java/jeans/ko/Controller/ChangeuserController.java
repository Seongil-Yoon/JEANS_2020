package jeans.ko.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Mod;
import jeans.ko.Dao.IUserDao;
import jeans.ko.Dto.PasswordDto;
import jeans.ko.Dto.UserDto;
import jeans.ko.Service.IFileService;
import jeans.ko.Service.IUtilService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userinformation")
public class ChangeuserController {
    //유저가 개인정보를 바꿀때 Controller
    private Logger logger = LoggerFactory.getLogger(ChangeuserController.class);

    @Autowired
    HttpSession session;

    @Autowired
    IFileService fileService;

    @Autowired
    IUtilService utilService;

    @Autowired
    IUserDao userDao;

    //작은 크기썸네일 식별을 위해 원본파일명 앞에 붙이는 식별자다.
    @Value("${profileSmallheader}")
    String smallHeader;

    //중간 크기썸네일 식별을 위해 원본파일명 앞에 붙이는 식별자다.
    @Value("${profileMiddleheader}")
    String middleHeader;

    //비밀번호 변경
    @PostMapping(value = "/password")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody PasswordDto passwordDto, BindingResult result) throws Exception {
        logger.info("changePassword메소드");

        if (result.getFieldError("ps") != null) {
            System.out.println("Error! = " + result.getFieldError("ps").getDefaultMessage());
        }
        //세션값을 통해 유저의 id값을 알아낸다.
        String userid = (String) session.getAttribute("userid");
        //user의 비밀번호 변경
        userDao.setPassword(userid, passwordDto.getPs());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //프로필썸네일 이미지 변경
    @PostMapping(value = "/profilethumbnail")
    public ResponseEntity<Void> changeprofileThumbnail(MultipartFile file) throws Exception {
        logger.info("changeprofileThumbnail메소드");
        //세션값을 통해 유저의 id값을 알아낸다.
        String id = (String) session.getAttribute("userid");
        //usertoPath는 유저의 id 를 통해 유저의 profile 폴더까지의 경로를 List<String>으로 반환한다.
        List<String> path = utilService.usertoPath(id);
        //현 유저의 프로필 사진을 얻어온다.
        String picture = userDao.getPicture(id);

        List<String> pictures = new ArrayList<>();
        pictures.add(picture);
        pictures.add(smallHeader + picture);
        pictures.add(middleHeader + picture);

        /*
            먼저 그 전의 유저의 프로필썸네일을 제거한다.
            첫번째 매개변수 path는 profile 까지의 경로
            두번째 매개변수 pictures리스트에는 원본파일명, s_원본파일명, m_원본파일명이다.
        */
        fileService.rmFiles(path, pictures);

        //유저DB의 사진값을 새로 올리려는 사진으로 바꾼다.
        userDao.setPicture(id, file.getOriginalFilename());

        //path에 files를 업로드한다.
        fileService.uploadFile(path,file);
        /*
            위 uploadfFiles는 단순히 파일만 업로드한다.
            위에서 업로드한 파일을 통해 썸네일 이미지를 따로 생성해 주어야한다.
         */
        fileService.mkProfilethumbnail(path, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //회원정보수정페이지에서 유저가 현재등록된 자신의 정보를 알수있도록하기 위한 메소드
    @GetMapping(value = "/information")
    public ResponseEntity<UserDto> getUserdto() {
        logger.info("getUserdto()메소드");
        UserDto userDto = new UserDto();
        userDto.setSex((int) session.getAttribute("usersex"));
        userDto.setNickname((String) session.getAttribute("usernickname"));
        userDto.setHeight((Integer) session.getAttribute("userheight"));
        userDto.setWeight((Integer) session.getAttribute("userweight"));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    //회원정보수정페이지에서 회원정보를 변경
    @PostMapping(value = "/information")
    public ResponseEntity<Void> setUserdto(@RequestBody UserDto userDto) {
        logger.info("setUserdto()메소드");
        userDto.setUserid((String) session.getAttribute("userid"));
        userDao.setChangeuser(userDto);
        session.setAttribute("usersex", userDto.getSex());
        session.setAttribute("usernickname", userDto.getNickname());
        session.setAttribute("userheight", userDto.getHeight());
        session.setAttribute("userweight", userDto.getWeight());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/deleteUser")
    public ResponseEntity deleteUser(@Valid @RequestBody PasswordDto passwordDto, BindingResult result) {
       logger.info("deleteUser메소드");
        if (result.getFieldError("ps") != null) {
            System.out.println("Error! = " + result.getFieldError("ps").getDefaultMessage());
        }

        //세션값을 통해 유저의 id값을 알아낸다.
        String userid = (String) session.getAttribute("userid");

        //유저가 입력한 비밀번호, 세션을 통해 얻은 id를 UserDto로 만든다.
        UserDto testuserDto=new UserDto();
        testuserDto.setUserid(userid);
        testuserDto.setPassword(passwordDto.getPs());

        //유저의 진짜 비밀번호.
        String password=userDao.userLogin(testuserDto);

        //유저의 진짜 비밀번호와 유저가 입력한 비밀번호가 맞다면 해당 유저를 지운다
        if(password.equals(passwordDto.getPs())){
            session.invalidate();

            List<String> path = utilService.usertoPath(userid);
            String picture = userDao.getPicture(userid);
            List<String> pictures = new ArrayList<>();
            pictures.add(picture);
            pictures.add(smallHeader + picture);
            pictures.add(middleHeader + picture);

            userDao.deleteUser(userid);
            fileService.rmFiles(path,pictures);
            fileService.rmDir(path);
            //path의 마지막은 profile이기 때문에 profile을 제거해줘야한다.
            path.remove(path.size()-1);
            fileService.rmDir(path);
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.BAD_GATEWAY);
        }

    }
}