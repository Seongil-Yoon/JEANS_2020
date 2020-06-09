package jeans.ko.Service;

import jeans.ko.Dao.ICommentDao;
import jeans.ko.Dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService {

    @Autowired
    ICommentDao commentDao;

    @Override
    public void insert(CommentDto commentDto) {
         commentDto.setParent_comment_id(2); //대댓글 만들때 수정해야됨
         commentDto.setDate("작성날짜"); //이것도 수정해야됨

        commentDao.insert(commentDto);
    }

}
