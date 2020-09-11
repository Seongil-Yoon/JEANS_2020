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
        List<String>moodList=new ArrayList<String>();
        for(MoodDto moodDto:moodDtoList){
           // moodList.add(moodDto.getMood());
        }

        List<MoodDto>test=new ArrayList<MoodDto>();
        MoodDto m1=new MoodDto();
        m1.setMood("minimal");
        MoodDto m2=new MoodDto();
        m2.setMood("street");
        test.add(m1);
        test.add(m2);
        System.out.println("test = " + test);
       // pretreatmentDao.countPretreatment(test,userid);
        pretreatmentDao.upMinimal(userid);
    }
}
