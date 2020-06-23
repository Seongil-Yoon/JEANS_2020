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
    //전체 게시판 리스트
    public List<BoardDto> list();
    //게시글 작성
    public int insert(BoardDto boardDto);
    //게시글 상세보기
    public BoardDto view(@Param("look_num") int look_num) ;
    //게시글 삭제
    public int delete(@Param("look_num") int look_num) ;
    //게시글 조회수 증가
    public void countUpdate(@Param("look_num") int look_num) ;
}
