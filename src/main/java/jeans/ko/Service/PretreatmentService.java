package jeans.ko.Service;

import jeans.ko.Dao.IMoodDao;
import jeans.ko.Dao.IPretreatmentDao;
import jeans.ko.Dto.MoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PretreatmentService implements IPretreatmentService {

    @Autowired
    IMoodDao moodDao;

    @Autowired
    IPretreatmentDao pretreatmentDao;

    @Override
    public void countUp(int looknum,String userid) {
        List<MoodDto>moodDtoList =moodDao.getMooddto(looknum);
        for(MoodDto moodDto:moodDtoList){
            switch(moodDto.getMood()){
                case "미니멀":
                    pretreatmentDao.upMinimal(userid);
                    break;
                case "스트리트":
                    pretreatmentDao.upStreet(userid);
                    break;
                case "캐쥬얼":
                    pretreatmentDao.upCasual(userid);
                    break;
                case "그런지":
                    pretreatmentDao.upGrunge(userid);
                    break;
                case "시티보이":
                    pretreatmentDao.upCityboy(userid);
                    break;
                case "테크웨어":
                    pretreatmentDao.upTechwear(userid);
                    break;
                case "워크웨어":
                    pretreatmentDao.upWorkwear(userid);
                    break;
                case "아메카지":
                    pretreatmentDao.upAmekaji(userid);
                    break;
                case "밀리터리":
                    pretreatmentDao.upMilitary(userid);
                    break;
            }
        }
    }
}
