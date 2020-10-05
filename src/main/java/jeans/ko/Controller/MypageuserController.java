package jeans.ko.Controller;

import jeans.ko.Dao.IBoardDao;
import jeans.ko.Dao.IUserDao;

import jeans.ko.Dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.Map;


@Controller
public class MypageuserController {

    //유저가 개인정보를 바꿀때 Controller
    private Logger logger = LoggerFactory.getLogger(ChangeuserController.class);

    @Autowired
    IUserDao userDao;

    @Autowired
    IBoardDao boardDao;

    @Autowired
    HttpSession session;

    //마이페이지 이동. 웹에서 모델앤뷰
    @GetMapping("/mypageUser/{nickname}")
    public String mypageUser(@PathVariable String nickname, Model model) {
        model.addAttribute("nick",nickname);
        return "mypageUser";
    }

    //마이페이지 내 회원정보 요청
    @GetMapping("/information/{nick}")
    public ResponseEntity<Map> getUser(@PathVariable String nick){

        Map map=new HashMap();
        Map countMap=new HashMap();//유저가 현재쓴글, 팔로워,팔로잉숫자등을 저장
        //닉네임을 넘기고 해당유저의 모든 정보를 받아왔다.
        UserDto userDto=userDao.getUserinformation(nick);

        //반환해야하는 정보를 set메소드를 통해 지정한 다음 넘기자
        UserDto returnUserdto=new UserDto();
        returnUserdto.setSex(userDto.getSex());
        returnUserdto.setUserid(userDto.getUserid());
        returnUserdto.setNickname(userDto.getNickname());
        returnUserdto.setWeight(userDto.getWeight());
        returnUserdto.setHeight(userDto.getHeight());
        returnUserdto.setMessage(userDto.getMessage());
        returnUserdto.setPrivacy(userDto.getPrivacy());
        returnUserdto.setEmail(userDto.getEmail());
        map.put("user",returnUserdto);
        int boardnum=boardDao.getBoardnum(userDto.getNickname());//해당 유저가 쓴 글 총 개수
        countMap.put("board",boardnum);
        map.put("count",countMap);
        return new ResponseEntity<Map>(map,HttpStatus.OK);
    }


    @PutMapping("/information/{memo}")
    public ResponseEntity<String> changeMemo(@PathVariable String memo){
        String userid=(String)session.getAttribute("userid");
        if(userid!=null) {
            userDao.setMessage(userid, memo);
            return new ResponseEntity<>(memo,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("요청이 올바르지 않습니다",HttpStatus.BAD_REQUEST);
        }
    }

}
