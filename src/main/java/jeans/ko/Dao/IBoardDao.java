package jeans.ko.Dao;


import jeans.ko.Dto.BoardDto;
import jeans.ko.Dto.PictureDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface IBoardDao {
    //전체 게시판 리스트
    public List<BoardDto> list();

    //게시글 작성
    public int insert(BoardDto boardDto);
    //  public int insert(@Param("boardDto")BoardDto boardDto, @Param("lists")List<PictureDto>lists);

    //각 게시글에 저장된 사진들 내역 DB에 저장
    public int insertPicturedatabase(List<PictureDto> lists);

    //게시글 상세보기
    public BoardDto view(@Param("look_num") int look_num);

    //게시글 삭제
    public int delete(@Param("look_num") int look_num);

    //게시글 조회수 증가
    public void countUpdate(@Param("look_num") int look_num);

    //게시글 수정
    public int update(BoardDto boardDto);
}
