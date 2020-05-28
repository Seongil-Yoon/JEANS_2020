package jeans.ko.Controller;

import jeans.ko.Dao.IBoardDao;
import jeans.ko.Service.BoardService;
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
 
    @Autowired
    IBoardDao boardDao;

    @RequestMapping("main")
    public String main(Model model){
        //value객체를 name이름으로 추가한다 ,메인 화면에 리스트 호출
        model.addAttribute("list",boardDao.list());
        return "/main";
    }

    @RequestMapping("test")
    public String test(Model model){
        return "/look_info";
    }


    //처음 들어오면 메인 화면
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("list",boardDao.list());
        return "/main";
    }

    @RequestMapping("/loginUser")
    public String loginUser() {
        return "loginUser";
    }

}