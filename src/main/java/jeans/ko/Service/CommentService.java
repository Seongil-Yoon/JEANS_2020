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

    @Autowired
    ICommentService commentService;


    @Override
    public CommentDto insert(CommentDto commentDto) {

       commentDao.insert(commentDto);

       //selectKey 해서 새로 등록한 댓글 기본키 값으로 새로등록된 댓글 가져오기
        return commentDao.comment(commentDto.getComment_id());
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

    @Override
    public List<CommentDto> childList(int comment_id) {
        return commentDao.childList(comment_id);
    }

}
