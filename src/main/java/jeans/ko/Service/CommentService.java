package jeans.ko.Service;

import jeans.ko.Dao.ICommentDao;
import jeans.ko.Dto.BoardDto;
import jeans.ko.Dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {

    @Autowired
    ICommentDao commentDao;

    @Override
    public int insert(CommentDto commentDto) {
         commentDto.setParent_comment_id(2); //대댓글 만들때 수정해야됨
         commentDto.setDate("작성날짜"); //이것도 수정해야됨

       return commentDao.insert(commentDto);
    }

    @Override
    public List<CommentDto> list(int look_num) {
        return commentDao.list(look_num);
    }

}
