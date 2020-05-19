package jeans.ko.Dao;

import jeans.ko.Dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Mapper
@Repository
public class BoardDao implements IBoardDao {

    @Override
    public void insertBoard(Map<String,String> paramMap) {

    }

}
