package jeans.ko.Service;

import jeans.ko.Dao.IBoardDao;
import lombok.extern.flogger.Flogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilService implements IUtilService {
    @Autowired
    IBoardDao iBoardDao;

    private Logger logger = LoggerFactory.getLogger(UtilService.class);

    //looknum을 받아서 looknum폴더까지의 패스를 List형식으로 반환하다.
    //이때 list에 년,월,룩번호가 담겨서 보내진다.
    @Override
    public List<String> looknumtoPath(int looknum) {
        logger.info("looknumtoPath 메소드 : 월까지 패스 반환");
        List<String> path = new ArrayList<String>();
        //Look테이블에 날짜에관한게 0000년 00월 00일 이런 식으로 저장되있어서 이런 식으로 뽑아 낼수밖에 없었다.
        String date = iBoardDao.getLook_date(looknum);
        String year = date.substring(0, 4);
        path.add(year);//년
        String month = date.substring(6, 8);
        path.add(month);//월
        path.add(Integer.toString(looknum));//룩번호
        return path;
    }

    //룩번호를 매개변수로 받음으로 해당 글에 있는 모든 사진들의 이름을 반환한다.
    @Override
    public List<String> looknumtoallPicturename(int look_num) {
        logger.info("looknumtoallPicturename 메소드 : 전체사진이름리스트반환");
        List<String> e = iBoardDao.getallPicturename(look_num);
         return e;
    }
}
