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

    private Logger logger = LoggerFactory.getLogger(UtilService.class);

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

    //looknum을 받아서 looknum폴더까지의 패스를 List형식으로 반환하다.
    //이때 list에 년,월,룩번호가 담겨서 보내진다.
    @Override
    public List<String> looknumtoPath(int looknum) {
        logger.info("looknumtoPath 메소드 : 월까지 패스 반환");
        List<String> path = new ArrayList<String>();
        path.add(directory);
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

    //유저의 id를 매개변수로 받은 뒤  나머지 값들과 함께 list에 합쳐 유저의 썸네일 이미지가 저장된 폴더까지의 경로를 반환한다
    @Override
    public List<String> usertoPath(String userid) {
        List<String>paths=new ArrayList<String>();
        paths.add(directory);
        paths.add(userid);
        paths.add(profile);
        return paths;
    }

    //String 리스트를 받아 다 합쳐서 하나의 path로 반환한다.
    @Override
    public String completePath(List<String> paths) {
        String path="";
        for(String i:paths){
            path+=i+route;
        }
        return path;
    }
}
