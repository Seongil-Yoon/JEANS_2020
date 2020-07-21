package jeans.ko.Controller;

import jeans.ko.Dao.IBoardDao;
import jeans.ko.Service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
public class MainController {

    private Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    IBoardDao boardDao;

    @RequestMapping("main")
    public String main(Model model) {
        logger.info("main진입");
        //value객체를 name이름으로 추가한다 ,메인 화면에 리스트 호출
        model.addAttribute("list", boardDao.list());
        return "/look_list";
    }

    //처음 들어오면 메인 화면
    @RequestMapping("/")
    public String index(Model model) {
        logger.info("index()진입");
        return "/intro";
    }

    @RequestMapping("/loginUser")
    public String loginUser() {
        logger.info("loginUser()진입");
        return "loginUser";
    }

}