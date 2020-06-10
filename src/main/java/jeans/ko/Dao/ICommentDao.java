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
    public void insert(CommentDto commentDto);

    public List<CommentDto> list(int look_num);

}
