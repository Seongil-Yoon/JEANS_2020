package jeans.ko.Dao;

import jeans.ko.Dto.MoodDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface IPretreatmentDao {
    public void insertUser(String userid);

    public void countPretreatment(List<MoodDto> moodList, String userid);

    public void upMinimal(String userid);

    public void upStreet(String userid);

    public void upCasual(String userid);

    public void upGrunge(String userid);

    public void upCityboy(String userid);

    public void upTechwear(String userid);

    public void upWorkwear(String userid);

    public void upAmekaji(String userid);

    public void upMilitary(String userid);
}
