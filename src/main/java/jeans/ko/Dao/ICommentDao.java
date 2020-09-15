package jeans.ko.Dao;

import jeans.ko.Dto.BoardDto;
import jeans.ko.Dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface ICommentDao {
    //댓글작성
    public int insert(CommentDto commentDto);
    //댓글 하나 정보 가져오기
    public CommentDto comment(int comment_id);
    //댓글 10개 정보 가져오기
    public List<CommentDto> list(int fk_look_num_Look_look_num , int comment_id);

    public int delete (int comment_id);

    public int update(int comment_id,String comment_content);

    //대댓글 가져오기
    public List<CommentDto> childList(int comment_id);

}
