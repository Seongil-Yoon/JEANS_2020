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

    @RequestMapping("/main")
    public String main(Model model) {

        return "/look_list";
    }

    //처음 들어오면 메인 화면
    @RequestMapping("/")
    public String index(Model model) {

        return "/intro";
    }

    @RequestMapping("/loginUser")
    public String loginUser() {

        return "loginUser";
    }

}