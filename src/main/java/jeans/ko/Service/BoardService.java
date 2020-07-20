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

    @Autowired
    IFileService iFileService;

    @Autowired
    IUtilService iUtilService;

    public void insert(BoardDto boardDto, List<MultipartFile> files) throws Exception {
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
        //여기서 look 데이터베이스에 정보가 입력된다.
        boardDao.insert(boardDto);

        //2.파일리스트들의 내용을 pictureDB에 입력. 단 1번에서 PK값을 가져온다.
        //boardDao.insert()메소드가 실행된후, boardDto에 입력된 look_num을 받는다.
        //그 후 pictureDB에 그 값과 함께 값을 넣는다.
        boardDao.insertPicturedatabase(bindingPicture(files, boardDto.getLook_num()));

        //여기서... 이제 년/월/룩번호를 뺏다
        List<String> path = iUtilService.looknumtoPath(boardDto.getLook_num());

        //3.makepictureDir을 통해 ${directory}/년/월/룩번호 까지 폴더를 생성한다.
        //uploadPictures에 파일리스트, makepictureDir을 통해 만든 경로, 글의 pk looknum을 매개변수로 입력한다.
        //${directory}/년/월/looknum(pk값) 안에 이미지가 업로드 된다.
        String pathPicture = iFileService.makepictureDir(path);
        iFileService.uploadPictures(files, pathPicture);

    }

    //글을 지운다.
    @Override
    public int delete(int look_num) {
        //1. boardDao를 통해. 년/월/룩번호를 반환받는다.
        List<String> path = iUtilService.looknumtoPath(look_num);

        //2. FileService의 deleteFiles를 통해 업로드되어있는 사진을 지우겠다.
        //looknumtoAllPicturename 메소드를 통해 룩번호를 매개변수로 주고 해당되는 글의 모든 사진의 이름을 반환받는다.
        List<String> files = iUtilService.looknumtoallPicturename(look_num);
        //deleteallFiles는 폴더 내의 모든 사진을 지운후 look_num에 해당되는 파일도 지운다.

        //deleteallFiles에 매개변수로 룩번호까지의 패스와 지울파일(해당글의 전체파일들)들을 보낸다.
        iFileService.deleteallFiles(path, files);

        return boardDao.delete(look_num);
    }

    @Override
    public int update(BoardDto boardDto) {
        boardDto.setPicture("사진"); //사진수정 수정해야함
        return boardDao.update(boardDto);
    }

    //Multipart형식으로 들어온 리스트들을 PictureDto리스트로 반환하는 메소드
    //매개변수 looknum은 외래키로 사용할
    @Override
    public ArrayList<PictureDto> bindingPicture(List<MultipartFile> e, int looknum) {
        ArrayList<PictureDto> lists = new ArrayList<PictureDto>();
        int j = 0;
        for (MultipartFile i : e) {
            PictureDto pictureDto = new PictureDto();
            String name = i.getOriginalFilename();
            long size = i.getSize();
            pictureDto.setLookNum(looknum);
            pictureDto.setPictureName(name);
            pictureDto.setPictureSize(size);
            lists.add(pictureDto);
        }
        return lists;
    }


}
