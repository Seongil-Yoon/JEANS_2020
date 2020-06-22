package jeans.ko.Controller;

import jeans.ko.Dao.IBoardDao;
import jeans.ko.Dto.BoardDto;
import jeans.ko.Service.CommentService;
import jeans.ko.Service.IBoardService;
import jeans.ko.exception.NotFoundException;
import jeans.ko.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;


@Controller
public class LookController {

    @Autowired
    HttpSession session;
    @Autowired
    CommentService commentService;
    @Autowired
    IBoardService boardService;
    @Autowired
    IBoardDao boardDao;

    //게시판 작성페이지 이동
    @RequestMapping("/look_write")
    public String look_write()
    { return "look_write"; }

    //게시판 상세보기 model and view 웹용
    @RequestMapping("/look")
    public String view(@RequestParam("look_num")int look_num ,Model model)
    {
        boardDao.countUpdate(look_num); //글상세보기 하면 조회수 증가
        model.addAttribute("view",boardDao.view(look_num)); //게시글정보가져오기
        model.addAttribute("comment",commentService.list(look_num)); //게시글에 댓글정보가져오기
        return "look_info";
    }

    @ResponseBody
    @GetMapping("/looks")  //룩 전체 리스트
    public List<BoardDto> searchAllLook(){
        return boardDao.list();
    }

    @ResponseBody
    @GetMapping("/looks/{id}") //안드로이드에 값주게 json 데이터만 넘기는용
    public  HashMap<String,Object> searchLook(@PathVariable int id)
    {   //looks/1   looks/3  -->String으로 오는데 int id 해서 int 로 변환해서 받음
        HashMap<String, Object> map = new HashMap<String, Object>();
        //게시글 가져오기
        BoardDto boardDto=boardDao.view(id);
        if(boardDto==null){
            //게시글이 없으면 not found 에러 return
            throw new NotFoundException(String.format("ID[%s] not found",id));
        }
        map.put("look",boardDto); //게시글 가져오기
        map.put("lookCommentList", commentService.list(id)); //댓글정보 가져오기
        boardDao.countUpdate(id); //글상세보기 하면 조회수 증가

        return map;
    }

    //삭제
    @ResponseBody
    @DeleteMapping("/looks/{id}")
    public void deleteLook (@PathVariable int id)  {
     //먼저 게시글이 있는지 확인
     BoardDto boardDto;
     boardDto=boardDao.view(id);
     //스크립트에서 1차로 차단하지만 서버로 바로접근하는 경우 차단용
     if(session.getAttribute("userid")!=boardDto.getFk_userid_user_userid()){
         throw new UnauthorizedException(String.format("unauthorized you"));
     }
     //게시글이 없으면 not found 오류 출력
     if(boardDto==null){
         throw new NotFoundException(String.format("ID[%s] not found",id));
     }else {
         //게시글이 있으면 삭제
         boardService.delete(id);
     }
    }

    //게시판 작성값 처리
    @PostMapping("/boardWriteRequest") //커멘드 객체로 값받아옴 BindingResult 는 오류값 출력
    public String boardWrite(@Valid BoardDto boardDto, BindingResult result) {

        if(result.hasErrors()) {
            if(result.getFieldError("title")!=null)
                System.out.println(result.getFieldError("title").getDefaultMessage());

            if(result.getFieldError("season")!=null)
                System.out.println(result.getFieldError("season").getDefaultMessage());

            if(result.getFieldError("look_public")!=null) {
                System.out.println(result.getFieldError("look_public").getDefaultMessage());
            }
            if(result.getFieldError("memo")!=null)
                System.out.println(result.getFieldError("memo").getDefaultMessage());

            if(result.getFieldError("tag")!=null)
                System.out.println(result.getFieldError("tag").getDefaultMessage());

            //입력안된오류전달
            session.setAttribute("error","입력안된사항이 있습니다");

            return "look_write";  //다시작성하기
        }

        boardService.insert(boardDto);

        return "redirect:/main"; //게시판 작성해서 재요청
    }

}



