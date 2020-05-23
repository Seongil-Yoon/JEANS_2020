package jeans.ko.Controller;

import jeans.ko.Dao.IBoardDao;
import jeans.ko.Dto.BoardDto;
import jeans.ko.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Autowired
    IBoardDao boardDao;

    //게시판 작성페이지
    @RequestMapping("/writeForm")
    public String writeForm()
    { return "writeForm"; }

    //게시판 상세보기
    @RequestMapping("/view")
    public String view(@RequestParam("look_num")int look_num ,Model model)
    {
        boardDao.countUpdate(look_num); //글상세보기 하면 조회수 증가
        model.addAttribute("view",boardDao.view(look_num));
        return "boardInfo";
    }

    //삭제
    @RequestMapping("/delete")
    public String delete(@RequestParam("look_num")int look_num)
    {
        boardService.delete(look_num);
        return "redirect:/main";
    }

    //게시판 작성값 처리
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



