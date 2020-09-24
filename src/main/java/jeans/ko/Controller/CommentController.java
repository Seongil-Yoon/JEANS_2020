package jeans.ko.Controller;


import jeans.ko.Dao.IBoardDao;
import jeans.ko.Dao.ICommentDao;
import jeans.ko.Dto.BoardDto;
import jeans.ko.Dto.CommentDto;
import jeans.ko.Service.IBoardService;
import jeans.ko.Service.ICommentService;
import jeans.ko.exception.BadRequestException;
import jeans.ko.exception.BindingResultException;
import jeans.ko.exception.NotFoundException;
import jeans.ko.exception.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//테스트 확인 주석
@Controller
public class CommentController {
    private Logger logger = LoggerFactory.getLogger(CommentController.class);

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
    @GetMapping("/look_comment/{comment_id}")
    public CommentDto comment(@PathVariable int comment_id) {

        logger.info("comment()진입");

        CommentDto commentDto = commentDao.comment(comment_id);

        if (commentDto == null) {
            //찾는 댓글이 없으면 not found 404 에러
            throw new NotFoundException(String.format("comment_id[%d] not found", comment_id));
        } else {
            return commentDto;
        }

    }

    //댓글 10개씩 리스트로 보내주기
    @ResponseBody
    @GetMapping("/look_comment_list/{fk_look_num_Look_look_num}/{comment_id}")
    public List<CommentDto> commentList(@PathVariable int fk_look_num_Look_look_num,
                                        @PathVariable int comment_id) {

        logger.info("commentList()진입");

        if (boardDao.view(fk_look_num_Look_look_num) == null) {
            //게시글 이 없으면 not found 404 에러
            throw new NotFoundException(String.format
                    ("fk_look_num_Look_look_num[%d] not found", fk_look_num_Look_look_num));
        }

        //게시판 에 댓글 리스트 10개를 전달
        return commentService.list(fk_look_num_Look_look_num,comment_id);
    }

    @ResponseBody
    @DeleteMapping("look_comment/{comment_id}")
    public void deleteLookComment(@PathVariable int comment_id) {
        logger.info("deleteLookComment()진입");

        //삭제할 댓글이 있는지 확인
        CommentDto commentDto = commentDao.comment(comment_id);

        if (commentDto == null) {
            //찾는 댓글이 없으면 not found 404 에러
            throw new NotFoundException(String.format("comment_id[%d] not found", comment_id));
        } else if (commentDto.getComment_sender_id().equals(session.getAttribute("userid")) == false
                || session.getAttribute("userid") == null) {
            throw new UnauthorizedException("unauthorized you");
        } else {
            //로그인한 아이디와 작성자 아이디가 같아서 댓글삭제
            commentService.delete(comment_id);
        }

    }

    //댓글 작성
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody //이거없으면 스프링은 반환형태를 뷰로 판단함
    @PostMapping("/look_comment")
    public CommentDto commentWrite(@RequestBody CommentDto commentDto,BindingResult result) {
        logger.info("commentWrite()진입");
        //유효성 검사에서 -2 한 이유는 날짜와 기본키는 데이터 넣으면서
        // 생성되기 떄문에 에러카운트가 2로 넘어와서 -2함
        new BindingResultException(result.getErrorCount()-2);

        if (session.getAttribute("userid") != null) {

          return  commentService.insert(commentDto);

        } else {
            //권한없음 오류
            throw new UnauthorizedException(String.format("unauthorized you"));
        }

    }

    //댓글 수정
    //Patch 는 부분 수정할떄 사용 put 은 전체 수정할떄 사용
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PatchMapping("look_comment/{comment_id}")
    public CommentDto updateLookComment(@PathVariable int comment_id,
                                        @RequestBody CommentDto updateDto,
                                        BindingResult result) {
        logger.info("updateLookComment()진입");

        //유효성 검사 에서 -5 한 이유는 수정은 content 만 넘어 와서 기본적 으로 -5가 되어 있음
        new BindingResultException(result.getErrorCount()-5);

        //댓글정보 가져오기
        CommentDto dto = commentDao.comment(comment_id);
        if (dto == null) {
            //수정할 댓글이 없으면 not found 404 에러
            throw new NotFoundException(String.format("comment_id[%d] not found", comment_id));
        }else if (dto.getComment_sender_id().equals(session.getAttribute("userid")) == false ||
                session.getAttribute("userid") == null) {

            throw new UnauthorizedException("unauthorized you");
        } else {
            //작성자 아이디와 세션 아이디가 같아 수정가능
            commentService.update(comment_id, updateDto.getComment_content());
        }
        //수정된 댓글정보 넘겨주기
        return commentDao.comment(comment_id);
    }

    //댓글 더보기 클릭하면 대댓글 리스트 보내주기
    @ResponseBody
    @GetMapping("/child_comment/{comment_id}")
    public List<CommentDto> childList(@PathVariable int comment_id) {

        CommentDto commentDto = commentDao.comment(comment_id);

        if (commentDto == null) {
            //찾는 댓글이 없으면 not found 404 에러
            throw new NotFoundException(String.format("comment_id[%d] not found", comment_id));
        } else {
            return commentService.childList(comment_id);
        }

    }

}
