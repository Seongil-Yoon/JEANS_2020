package jeans.ko.Dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CommentDto {

    private int comment_id;
    private int fk_look_num_Look_look_num ;
    private String  comment_content;
    private String  comment_sender_name; //작성자 닉네임
    private String  comment_sender_id; //작성자 아이디
    private String date;
    private int parents; //대댓글 사용위한 변수 대댓글 아닌변수는 값 0
    private int ref_count; //자식댓글 갯수 변수

}
