package jeans.ko.Service;

import jeans.ko.Controller.UserController;
import jeans.ko.Dao.IBoardDao;
import jeans.ko.Dto.BoardDto;
import jeans.ko.Dto.PictureDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BoardService implements IBoardService {

    private Logger logger = LoggerFactory.getLogger(BoardService.class);

    @Autowired
    private IBoardDao boardDao;

    @Autowired
    private PictureDto pictureDto;

    @Autowired
    HttpSession httpSession;

    public void insert(BoardDto boardDto/*, List<MultipartFile> files*/) {
        System.out.println("BoardService insert메소드 호출");
        //시간을 내가 원하는 형식으로 출력
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일");
        String look_date = formatter.format(new Date());
        boardDto.setCount(0); //조회수 초기값 0줌
        boardDto.setLook_date(look_date); //작성 시간 넣기
        boardDto.setFk_userid_user_userid((String) httpSession.getAttribute("userid"));
        boardDto.setNickname((String) httpSession.getAttribute("usernickname"));
        boardDto.setLike(1); //좋아요 값넣기 수정해야함
        boardDto.setComment_count(2);//댓글수 수정해야함

        //1. 사용자가 입력한 글내역 중 파일들을 제외하고 나머지 내용들을 입력.
        boardDao.insert(boardDto);

        //2.파일리스트들의 내용을 pictureDB에 입력. 단 1번에서 PK값을 가져온다.


        //3.파일들을 지정된경로에 업로드한다. 업로드 전략은

        //  boardDao.insertPicturedatabase(bindingPicture(files));
        //  return boardDao.insert(boardDto);
    }

    @Override
    public int delete(int look_num) {
        return boardDao.delete(look_num);
    }

    @Override
    public int update(BoardDto boardDto) {
        boardDto.setPicture("사진"); //사진수정 수정해야함
        return boardDao.update(boardDto);
    }

    @Override
    public ArrayList<PictureDto> bindingPicture(List<MultipartFile> e) {
        ArrayList<PictureDto> lists = new ArrayList<PictureDto>();
        for (MultipartFile i : e) {
            PictureDto pictureDto = new PictureDto();
            String name = i.getOriginalFilename();
            long size = i.getSize();
            pictureDto.setPictureName(name);
            pictureDto.setPictureSize(size);
            System.out.println("name + size = " + name + size);
            lists.add(pictureDto);
        }
        /*for (int i = 0; i < e.size(); i++) {
            String name = e.get(0).getOriginalFilename();
            System.out.println("name = " + name);
            long size = e.get(0).getSize();
            pictureDto.setPictureName(name);
            pictureDto.setPictureSize(size);
            lists.add(pictureDto);
            System.out.println("pictureDto = " + lists.get(i));
        }*/
        for (int i = 0; i <= 1; i++) {
            System.out.println("i.getPictureName() = " + lists.get(i).getPictureName());
        }
        return lists;
    }


}
