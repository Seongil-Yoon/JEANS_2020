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
}
