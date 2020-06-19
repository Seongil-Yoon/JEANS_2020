package jeans.ko.Controller;


import jeans.ko.Dao.IBoardDao;
import jeans.ko.Dao.ICommentDao;
import jeans.ko.Dto.CommentDto;
import jeans.ko.Service.IBoardService;
import jeans.ko.Service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class CommentController {


    @Autowired
    ICommentService commentService;

    @Autowired
    CommentDto commentDto;

    @Autowired
    ICommentDao commentDao;

    @ResponseBody //이거없으면 스프링은 반환형태를 뷰로 판단함
    @RequestMapping("/commentWrite")
    public HashMap<String,Object> commentWrite(CommentDto commentDto) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(commentService.insert(commentDto)==1) {
            //댓글등록성공하면 새로고침안하고 등록한댓글 추가하기위해 댓글리스트 새로들고옴
            map.put("commentList",commentService.list(commentDto.getFk_look_num_Look_look_num()));
        }

        return map;
    }

    //@ResponseBody가 붙은 메서드에서 Map을 반환하면 자동으로 Map 정보가 JSON 객체로 변환되어 전송.
    @ResponseBody
    @RequestMapping("/commentList")
    public HashMap<String, Object> commentList(@RequestParam("look_num") int look_num) {
        HashMap<String, Object> map = new HashMap<String, Object>();

            map.put("commentList",commentDao.list(look_num));

        return map;
    }


}
