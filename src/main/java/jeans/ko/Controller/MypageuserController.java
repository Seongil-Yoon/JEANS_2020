package jeans.ko.Controller;

import jeans.ko.Dao.IUserDao;
import jeans.ko.Dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Controller
public class MypageuserController {

    //유저가 개인정보를 바꿀때 Controller
    private Logger logger = LoggerFactory.getLogger(ChangeuserController.class);

    @Autowired
    IUserDao userDao;

    //마이페이지 이동. 웹에서 모델앤뷰
    @GetMapping("/mypageUser/{nickname}")
    public String mypageUser(@PathVariable String nickname, Model model) {
        logger.info("mypageUser메소드");
        model.addAttribute("nick",nickname);
        System.out.println("nickname = " + nickname);
        return "mypageUser";
    }

    //마이페이지 내 회원정보 요청
    @GetMapping("/information/{nick}")
    public ResponseEntity<UserDto>getUser(@PathVariable String nick){
        logger.info("getUser메소드");
        //닉네임을 넘기고 해당유저의 모든 정보를 받아왔다.
        UserDto userDto=userDao.getUserinformation(nick);

        //반환해야하는 정보를 set메소드를 통해 지정한 다음 넘기자
        UserDto returnUserdto=new UserDto();
        returnUserdto.setWeight(userDto.getWeight());
        returnUserdto.setHeight(userDto.getHeight());
        returnUserdto.setMessage(userDto.getMessage());
        return new ResponseEntity<>(returnUserdto,HttpStatus.OK);
    }

    //마이페이지 내 메모 회원정보 변경 ->성일이가 만들고 난 후 기능 넣기

}
