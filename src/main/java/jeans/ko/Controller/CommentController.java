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
    @GetMapping("/look_comment_all/{look_num}")
    public List<CommentDto> commentList(@PathVariable int look_num) {

        if(boardDao.view(look_num)==null){
            //게시글이 없으면 not found 에러
            throw new NotFoundException(String.format("ID[%s] not found",look_num));
        }
        //게시판에 댓글 리스트를 전달
       return commentDao.list(look_num);
    }

    @ResponseBody
    @DeleteMapping("look_comment/{comment_id}")
    public void deleteLookComment(@PathVariable int comment_id){
        //삭제할 댓글이 있는지 확인
        CommentDto commentDto=commentService.comment(comment_id);

        if(commentDto==null){
            //찾는 댓글이 없으면 not found 404 에러
            throw new NotFoundException(String.format("comment_id[%s] not found",comment_id));
        }
        if(commentDto.getComment_sender_id().equals(session.getAttribute("userid"))==false
        ||session.getAttribute("userid")==null){
            throw new UnauthorizedException("unauthorized you");
        }else {
            //로그인한 아이디와 작성자 아이디가 같아서 댓글삭제
            commentService.delete(comment_id);
        }

    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody //이거없으면 스프링은 반환형태를 뷰로 판단함
    @PostMapping("/look_comment")
    public CommentDto commentWrite(@RequestBody CommentDto commentDto) {

        if(session.getAttribute("userid")!=null){
            commentService.insert(commentDto);
            //selectKey 해서 새로 등록한 댓글 기본키 값으로 새로등록된 댓글 가져오기
            return commentService.comment(commentDto.getComment_id());
        }else {
            //권한없음 오류
            throw new UnauthorizedException(String.format("unauthorized you"));
        }

    }

    //Patch 는 부분 수정할떄 사용 put 은 전체 수정할떄 사용
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PatchMapping("look_comment/{comment_id}")
    public CommentDto updateLookComment(@PathVariable int comment_id,@RequestBody CommentDto updateDto){
        //댓글정보 가져오기
        CommentDto dto=commentService.comment(comment_id);
        if(dto==null){
            //수정할 댓글이 없으면 not found 404 에러
            throw new NotFoundException(String.format("comment_id[%s] not found",comment_id));
        }
        if(dto.getComment_sender_id().equals(session.getAttribute("userid"))==false||
                session.getAttribute("userid")==null){
            //작성자 아이디와 세션 아이디가 달라서 권한없음 오류
            throw new UnauthorizedException("unauthorized you");
        }else {
            //작성자 아이디와 세션 아이디가 같아 수정가능
            commentService.update(comment_id,updateDto.getComment_content());
        }
        //수정된 댓글정보 넘겨주기
        return commentService.comment(comment_id);
    }
}
