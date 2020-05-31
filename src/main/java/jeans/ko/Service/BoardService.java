package jeans.ko.Service;

import jeans.ko.Dao.IBoardDao;
import jeans.ko.Dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BoardService implements IBoardService {

    @Autowired
    private IBoardDao boardDao;

    @Autowired
    HttpSession httpSession;

    public void insert(BoardDto boardDto) {

        //시간을 내가 원하는 형식으로 출력
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일");
        String look_date=  formatter.format(new Date());
        boardDto.setCount(0); //조회수 초기값 0줌
        boardDto.setLook_date(look_date); //작성 시간 넣기
        boardDto.setFk_userid_user_userid((String) httpSession.getAttribute("userid"));
        boardDto.setNickname((String)httpSession.getAttribute("usernickname"));
        boardDto.setLike(1); //좋아요 값넣기 수정해야함
        boardDto.setComment_count(2);//댓글수 수정해야함
        boardDto.setPicture("사진"); //사진 수정해야함

        boardDao.insert(boardDto);
    }

    @Override
    public void delete(int look_num,String look_viewUserId) {
         //url 접근하여 권한없는데 삭제하는거 방지용 로그인한 아이디와 작성자 아이디가 같아야 삭제가능
        if(httpSession.getAttribute("userid")!=look_viewUserId){
                return;
        }else {
            boardDao.delete(look_num);
        }
    }

}
