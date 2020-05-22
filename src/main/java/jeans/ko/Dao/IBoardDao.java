package jeans.ko.Dao;


import jeans.ko.Dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface IBoardDao {

    public List<BoardDto> list();

    public void insert(BoardDto boardDto);

    public void delete(@Param("look_num") int look_num) ;

}
