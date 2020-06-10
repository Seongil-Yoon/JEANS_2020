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


    @RequestMapping("/commentWrite")
    public String writeForm(CommentDto commentDto) {

        commentService.insert(commentDto);

        return "look_write";
    }

    //@ResponseBody가 붙은 메서드에서 Map을 반환하면 자동으로 Map 정보가 JSON 객체로 변환되어 전송.
    @ResponseBody
    @RequestMapping("/commentList")
    public HashMap<String, Object> commentList(@RequestParam("num") int num,@RequestParam("look_num") int look_num) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        List<CommentDto> list;
        list=commentService.list(look_num);
        //2개씩 뺌
        try {
            map.put("commentList1", list.get(num+1)); //두번쨰값 처음에 넣기
            //나중에들어간게 처음에 나오니 첫번째값을 두번쨰에넣음
            map.put("commentList2", list.get(num));
        }catch (Exception e){
            System.out.println("배열범위 넘어감");
        }

        return map;
    }


}
