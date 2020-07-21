package jeans.ko.Controller;

import jeans.ko.Dto.ChildCommentDto;
import jeans.ko.Service.ChildCommentService;
import jeans.ko.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpSession;

@Controller
public class ChildCommentController {

    HttpSession session;
    ChildCommentService ChildcommentService;

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping("/look_comment")
    public ChildCommentDto ChildCommentWrite(@RequestBody ChildCommentDto childCommentDto, BindingResult result) {

        if (result.getErrorCount() > 0) {
            //대댓글 입력 안된 부분이 있음
            throw new UnauthorizedException(String.format("childComment validation error"));
        }

        if(session.getAttribute("userid")!=null){
            ChildcommentService.insert(childCommentDto);
            //selectKey 해서 새로 등록한 대댓글 기본키 값으로 새로 등록된 대댓글 가져 오기
            return ChildcommentService.ChildComment(childCommentDto.getChild_comment_num());
        }else {
            //로그인 하지 않아 권한 없음 오류
            throw new UnauthorizedException(String.format("unauthorized you"));
        }

    }

}
