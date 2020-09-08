package jeans.ko.Dao;

import jeans.ko.Dto.MoodDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface IMoodDao {
    public List<MoodDto> getMooddto(@Param("look_num")int look_num);

    //각 게시글에 해당되는 무드 내역 DB에 저장
    public int insertMooddatabase(List<MoodDto> moodDtos);

    public void deleteMood(int look_num);
}