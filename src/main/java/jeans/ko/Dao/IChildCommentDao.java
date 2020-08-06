package jeans.ko.Dao;

import jeans.ko.Dto.ChildCommentDto;
import jeans.ko.Dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Mapper
public interface IChildCommentDao {

    //대댓글 작성
    public int insert(ChildCommentDto childCommentDto);
    //대댓글 하나 정보 가져오기
    public ChildCommentDto childComment(int child_comment_num);
    //대댓글 전체 정보 가져오기
    public List<ChildCommentDto> list(int fk_comment_id);

}
