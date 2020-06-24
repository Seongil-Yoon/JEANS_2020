package jeans.ko.Controller;


import jeans.ko.Dao.IBoardDao;
import jeans.ko.Dao.ICommentDao;
import jeans.ko.Dto.BoardDto;
import jeans.ko.Dto.CommentDto;
import jeans.ko.Service.IBoardService;
import jeans.ko.Service.ICommentService;
import jeans.ko.exception.NotFoundException;
import jeans.ko.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    HttpSession session;

    @Autowired
    IBoardDao boardDao;

    @Autowired
    ICommentService commentService;

    @Autowired
    CommentDto commentDto;

    @Autowired
    ICommentDao commentDao;

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody //이거없으면 스프링은 반환형태를 뷰로 판단함
    @PostMapping("/look_comment")
    public CommentDto commentWrite(CommentDto commentDto) {

        //로그인 해야 댓글 작성가능
        if(session.getAttribute("userid")!=null){
            commentService.insert(commentDto);
            //selectKey 해서 새로 등록한 댓글 기본키 값으로 새로등록된 댓글 가져오기
            return commentService.comment(commentDto.getComment_id());
        }else {
            //권한없음 오류
            throw new UnauthorizedException(String.format("unauthorized you"));
        }

    }

    @ResponseBody
    @GetMapping ("/look_comment/{comment_id}")
    public CommentDto comment(@PathVariable int comment_id) {

        CommentDto commentDto=commentService.comment(comment_id);

        if(commentDto==null){
            //찾는 댓글이 없으면 not found 404 에러
            throw new NotFoundException(String.format("ID[%s] not found",comment_id));
        }else {
            return commentDto;
        }

    }

    @ResponseBody
    @GetMapping("/look_comment_All/{look_num}")
    public List<CommentDto> commentList(@PathVariable int look_num) {

        if(boardDao.view(look_num)==null){
            //게시글이 없으면 not found 에러
            throw new NotFoundException(String.format("ID[%s] not found",look_num));
        }
        //게시판에 댓글 리스트를 전달
       return commentDao.list(look_num);
    }

}
