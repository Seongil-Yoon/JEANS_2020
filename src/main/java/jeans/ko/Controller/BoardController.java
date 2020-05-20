package jeans.ko.Controller;

import jeans.ko.Dto.BoardDto;
import jeans.ko.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @RequestMapping("/writeForm")
    public String writeForm()
    { return "writeForm"; }

    @PostMapping("/boardWriteRequest")
    public String boardWrite(BoardDto boardDto) {

        boardService.insert(boardDto);

        return "main"; //게시판 리스트 나오게 수정해야함
    }

}
