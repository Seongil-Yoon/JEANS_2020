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

       return commentDao.insert(commentDto);
    }

    @Override
    public CommentDto comment(int comment_id) {
        return commentDao.comment(comment_id);
    }

    @Override
    public List<CommentDto> list(int fk_look_num_Look_look_num,int comment_id) {
        //comment_id가 0이면 처음 게시글 들어간 초기값이여서 자바 최대값으로 최신 댓글 10개 가져옴
        if(comment_id==0){ comment_id=Integer.MAX_VALUE; }

        return commentDao.list(fk_look_num_Look_look_num,comment_id);
    }

    @Override
    public int delete(int comment_id) {
        return commentDao.delete(comment_id);
    }

    @Override
    public int update(int comment_id, String comment_content) {
        return commentDao.update(comment_id,comment_content);
    }

}
