package jeans.ko.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
public class MainController {

    @RequestMapping("/")
    public String test(){

        return "main";
    }

    @RequestMapping("/loginUser")
    public String loginUser() {

        return "loginUser";
    }


}