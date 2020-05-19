package jeans.ko.Service;

import jeans.ko.Dao.BoardDao;
import jeans.ko.Dao.IBoardDao;
import jeans.ko.Dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Service
public class BoardService {

    @Autowired
    private IBoardDao boardDao;

    public void insertBoard(Map<String,String> paramMap) {
        boardDao.insertBoard(paramMap);
    }

}
