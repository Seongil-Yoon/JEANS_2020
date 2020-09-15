package jeans.ko.Service;

import jeans.ko.Dao.IBoardDao;
import lombok.extern.flogger.Flogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilService implements IUtilService {
    @Autowired
    IBoardDao iBoardDao;

    @Value("${directory}")
    String directory;

    //유저 개인 폴더 밑에 유저의 기본프로필사진&썸네일사진들을 저장하는 파일의 이름이다.
    @Value("${profilepath}")
    String profile;

    //작은 크기썸네일 식별을 위해 원본파일명 앞에 붙이는 식별자다.
    @Value("${profileSmallheader}")
    String smallHeader;

    //중간 크기썸네일 식별을 위해 원본파일명 앞에 붙이는 식별자다.
    @Value("${profileMiddleheader}")
    String middleHeader;

    //위도우에서는 \\, 리눅스서버에서는/
    @Value("${route}")
    String route;

    private Logger logger = LoggerFactory.getLogger(UtilService.class);

    /*looknum을 받아서 looknum폴더까지의 패스를 List형식으로 반환하다.
    이때 list에 년,월,룩번호가 담겨서 보내진다.*/
    @Override
    public List<String> looknumtoPath(int looknum) {
        logger.info("looknumtoPath 메소드");
        List<String> path = new ArrayList<String>();
        //Look테이블에 날짜에관한게 0000년 00월 00일 이런 식으로 저장되있어서 이런 식으로 뽑아 낼수밖에 없었다.
        path.add(directory);
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


    /*
        path리스트는 구분자를 제외한 프로필사진 폴더까지의 경로모음 리스트다.
        첫번째로 기본경로까지, 두번째로 해당사용자의 아이디 마지막으로 profile폴더.
     */
    @Override
    public ArrayList<String> getProfilepath(String userid) {
        ArrayList<String> path = new ArrayList<String>();
        path.add(directory);
        path.add(userid);
        path.add(profile);
        return path;
    }

    @Override
    public String plusPath(List<String> paths) {
        logger.info("plusPath메소드");
        String completedPath="";
        for(String i:paths){
            completedPath+=i+route;
        }
        return completedPath;
    }
}
