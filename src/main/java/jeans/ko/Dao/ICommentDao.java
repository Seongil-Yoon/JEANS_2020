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

    public CommentDto comment(int comment_id);

    public List<CommentDto> list(int look_num);

    public int delete (int comment_id);

}
