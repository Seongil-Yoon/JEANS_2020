package jeans.ko.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String test(){
        return "main";
    }

    @RequestMapping("/loginUser")
    public String loginUser(){
        return "loginUser";
    }



    @RequestMapping("/writeForm")
    public String writeForm()
    { return "writeForm"; }

}