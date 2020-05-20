package jeans.ko.Service;

import jeans.ko.Dao.IBoardDao;
import jeans.ko.Dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BoardService {

    @Autowired
    private IBoardDao boardDao;


    public void insert(BoardDto boardDto) {

        //시간을 내가 원하는 형식으로 출력
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분 ss초");
        String look_date=  formatter.format(new Date());

        boardDto.setLook_date(look_date);
        boardDto.setUserid("asd");
        System.out.println("userid "+boardDto.getUserid());
        boardDao.insert(boardDto);

    }

}
