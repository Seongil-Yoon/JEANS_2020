package jeans.ko.Controller;

import jeans.ko.Dto.ChildCommentDto;
import jeans.ko.Service.ChildCommentService;
import jeans.ko.exception.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    HttpSession session;
    @Autowired
    ChildCommentService ChildcommentService;

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping("/look_ChildComment")
    public ChildCommentDto ChildCommentWrite(@RequestBody ChildCommentDto childCommentDto, BindingResult result) {
        
        if (result.getErrorCount() > 2) {
            //대댓글 입력 안된 부분 있으면 400에러  2이상인 이유는 처음 넘어올때
            // 작성 시간과 기본 키값이 2개는 기본 적으로 안넘어오기 떄문
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
