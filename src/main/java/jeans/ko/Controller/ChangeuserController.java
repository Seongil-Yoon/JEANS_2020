package jeans.ko.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userinformation")
public class ChangeuserController {
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

    @PostMapping(value = "/password")
    public ResponseEntity<String> changePassword(@Valid @RequestBody PasswordDto passwordDto, BindingResult result) throws Exception {
        System.out.println("passwordDto.getPs() = " + passwordDto.getPs());
        System.out.println("result.getErrorCount() = " + result.getErrorCount());
        System.out.println("result.hasGlobalErrors() = " + result.hasGlobalErrors());

        if (result.getFieldError("ps") != null) {
            System.out.println("Error! = " + result.getFieldError("ps").getDefaultMessage());
        }
        String userid= (String) session.getAttribute("userid");
        userDao.setPassword(userid,passwordDto.getPs());

        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @PostMapping(value="/profilethumbnail")
    public ResponseEntity<Void> changeprofileThumbnail(MultipartFile file) throws Exception {
        logger.info("changeprofileThumbnail메소드");
        String id=(String)session.getAttribute("userid");
        List<String> path=  utilService.usertoPath(id);

        String picture=userDao.getPicture(id);
        List<String>pictures=new ArrayList<>();
        pictures.add(picture);
        pictures.add(smallHeader+picture);
        pictures.add(middleHeader+picture);
        fileService.rmFiles(path,pictures);

        userDao.setPicture(id,file.getOriginalFilename());
        List<MultipartFile>files=new ArrayList<MultipartFile>();
        files.add(file);
        fileService.uploadFiles(path,files);
        fileService.mkProfilethumbnail(path,id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value="/information")
    public ResponseEntity<UserDto> getUserdto(){
        logger.info("information()메소드");
        UserDto userDto=new UserDto();
        userDto.setSex((int)session.getAttribute("usersex"));
        userDto.setNickname((String)session.getAttribute("usernickname"));
        userDto.setHeight((Integer)session.getAttribute("userheight"));
        userDto.setWeight((Integer)session.getAttribute("userweight"));
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @PostMapping(value="/information")
    public ResponseEntity<Void> setUserdto(@RequestBody UserDto userDto){
        logger.info("setUserdto()메소드");
        System.out.println("userDto = " + userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}