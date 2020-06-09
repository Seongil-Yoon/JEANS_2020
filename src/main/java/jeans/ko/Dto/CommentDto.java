package jeans.ko.Dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CommentDto {

    private int comment_id;
    private int fk_look_num_Look_look_num ;
    private int parent_comment_id; //대댓글 만들떄 부모테이블 0이면 기본댓글 0이상이면 댓글에 댓글임
    private String  comment_content;
    private String  comment_sender_name; //작성자 닉네임
    private String  comment_sender_id; //작성자 아이디
    private String date;

}
