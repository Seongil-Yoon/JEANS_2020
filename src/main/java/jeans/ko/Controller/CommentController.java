package jeans.ko.Controller;


import jeans.ko.Dao.IBoardDao;
import jeans.ko.Dao.ICommentDao;
import jeans.ko.Dto.CommentDto;
import jeans.ko.Service.IBoardService;
import jeans.ko.Service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {


    @Autowired
    ICommentService commentService;

    @Autowired
    CommentDto commentDto;


    @RequestMapping("/commentWrite")
    public String writeForm(CommentDto commentDto){


        System.out.println("작성내용  "+commentDto.getComment_content());
        System.out.println("작성자아이디 "+commentDto.getComment_sender_id());

        commentService.insert(commentDto);


        return "look_write";
    }

}
