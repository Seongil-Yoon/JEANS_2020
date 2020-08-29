package jeans.ko.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/mypageUser")
public class MypageuserController {
    //유저가 개인정보를 바꿀때 Controller
    private Logger logger = LoggerFactory.getLogger(ChangeuserController.class);

    //마이페이지 이동. 웹에서 모델앤뷰
    @GetMapping
    public String mypageUser(String nickname) {
        logger.info("mypageUser메소드");
        return "mypageUser";
    }

    //마이페이지 내 회원정보 요청


    //마이페이지 내 메모 회원정보 변경

}
