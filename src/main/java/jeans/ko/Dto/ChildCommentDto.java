package jeans.ko.Dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ChildCommentDto {

    private int child_comment_num; //기본키
    private int fk_comment_id ; //댓글 외래키
    private String  child_comment_content;
    private String  child_comment_id; //작성자 아이디
    private String  child_comment_nickname; //작성자 닉네임
    private String date;

}
