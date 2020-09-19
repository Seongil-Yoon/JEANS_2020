package jeans.ko.Dao;


import jeans.ko.Dto.BoardDto;
import jeans.ko.Dto.MoodDto;
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
    public List<BoardDto> list(@Param("look_num") int look_num);
    //글 검색
    public List<BoardDto> searchList(@Param("option")String option, @Param("keyword")String keyword);

    //게시글 작성
    public int insert(BoardDto boardDto);

    //각 게시글에 저장된 사진들 내역 DB에 저장
    public int insertPicturedatabase(List<PictureDto> files);

    public int insertPicture(String boardnum,String uuidname);

    //게시글 상세보기
    public BoardDto view(@Param("look_num") int look_num);

    //게시글 삭제
    public int delete(@Param("look_num") int look_num);

    public void deleteAllpictures(@Param("look_num")int look_num);

    //게시글 조회수 증가
    public void countUpdate(@Param("look_num") int look_num);

    //게시글 수정
    public int update(BoardDto boardDto);

    //look_num을 입력받아 look_date를 출력받는다
    public String getLook_date(int look_num);

    //look_num을 입력받아 picture테이블에서 해당 룩을 외래키로 가지고 있는 단 하나의 사진을 반환한다.
    public String getonePicturename(int look_num);

    //look_num을 입력받아 picture테이블에서 해당 룩을 외래키로 가지고 있는 모든 사진들을 반환한다.
    public List<String> getallPicturename(int look_num);

    //userID를 받아 그 유저가 쓴 모든 글 목록을 반환하는 메소드
    public List<BoardDto> getLooksforId(String userId);

    //user 닉네임을 받아 그 유저가 쓴 글 숫자를 반환하는 메소드
    public int getBoardnum(String nickname);
}