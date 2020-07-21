package jeans.ko.Service;

import jeans.ko.Dao.IChildCommentDao;
import jeans.ko.Dao.ICommentDao;
import jeans.ko.Dto.ChildCommentDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ChildCommentService implements IChildCommentService {

    @Autowired
    IChildCommentDao childCommentDao;

    @Override
    public int insert(ChildCommentDto childCommentDto) {
        return childCommentDao.insert(childCommentDto);
    }

    @Override
    public ChildCommentDto ChildComment(int child_comment_num) {
        return childCommentDao.childComment(child_comment_num);
    }

    @Override
    public List<ChildCommentDto> list(int fk_comment_id) {
        return childCommentDao.list(fk_comment_id);
    }
}
