package jeans.ko.Controller;

import jeans.ko.Dto.BoardDto;
import jeans.ko.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @RequestMapping("/writeForm")
    public String writeForm()
    { return "writeForm"; }

    @RequestMapping("/delete")
    public String delete(@RequestParam("look_num")int look_num)
    {
        boardService.delete(look_num);
        return "redirect:/main";
    }

    @PostMapping("/boardWriteRequest") //커멘드 객체로 값받아옴 BindingResult 는 오류값 출력
    public String boardWrite(@Valid BoardDto boardDto, BindingResult result) {

        if(result.hasErrors()) {
            if(result.getFieldError("title")!=null)
                result.getFieldError("title").getDefaultMessage();

            if(result.getFieldError("season")!=null)
                result.getFieldError("season").getDefaultMessage();

            if(result.getFieldError("look_public")!=null) {
                result.getFieldError("look_public").getDefaultMessage();
            }
            if(result.getFieldError("memo")!=null)
                result.getFieldError("memo").getDefaultMessage();

            if(result.getFieldError("tag")!=null)
                result.getFieldError("tag").getDefaultMessage();

            return "writeForm";  //다시작성하기
        }

        boardService.insert(boardDto);

        return "redirect:/main"; //게시판 작성해서 재요청
    }

}



