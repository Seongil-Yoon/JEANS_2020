package jeans.ko.Dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@Component
public class BoardDto {

    private int look_num; //오토 인크리먼트
    private String title;
    private int like;
    private String season;
    private String tag;
    private int look_public; //공개설정 1공개 2비공개
    private String userid;  //회원 id
    private String memo;   //null값 허용
    private int look_date;  //작성날짜
    private int count;    //조회수
    private int comment_count; //댓글수
    private String picture;

}
