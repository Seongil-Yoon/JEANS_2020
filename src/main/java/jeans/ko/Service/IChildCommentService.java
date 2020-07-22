package jeans.ko.Service;

import jeans.ko.Dto.ChildCommentDto;
import jeans.ko.Dto.CommentDto;

import java.util.List;

public interface IChildCommentService {

    public int insert(ChildCommentDto childCommentDto);
    //대댓글 하나 가져 오기
    public ChildCommentDto ChildComment(int child_comment_num);
    //대댓글 전체 가져 오기
    public List<ChildCommentDto> list(int fk_comment_id);

}
