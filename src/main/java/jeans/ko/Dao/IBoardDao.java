package jeans.ko.Dao;


import jeans.ko.Dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
public interface IBoardDao {

    public void insert(BoardDto boardDto);

}
