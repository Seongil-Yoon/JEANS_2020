package jeans.ko.Service;

import jeans.ko.Dto.BoardDto;
import jeans.ko.Dto.CommentDto;

import java.util.List;

public interface ICommentService {

    public void insert(CommentDto commentDto);

    public List<CommentDto> list(int look_num);

}
