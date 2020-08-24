package jeans.ko.Service;

import jeans.ko.Dto.BoardDto;
import jeans.ko.Dto.CommentDto;

import java.util.List;

public interface ICommentService {

    public int insert(CommentDto commentDto);

    public CommentDto comment(int comment_id);

    public List<CommentDto> list(int fk_look_num_Look_look_num,int comment_id);

    public int delete (int comment_id);

    public int update(int comment_id,String comment_content);
}
