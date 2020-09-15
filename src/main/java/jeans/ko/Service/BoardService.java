package jeans.ko.Service;

import jeans.ko.Controller.UserController;
import jeans.ko.Dao.IBoardDao;
import jeans.ko.Dto.BoardDto;
import jeans.ko.Dto.PictureDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
        logger.info("insert메소드");
        //시간을 내가 원하는 형식으로 출력
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일");
        String look_date = formatter.format(new Date());
        boardDto.setCount(0); //조회수 초기값 0줌
        boardDto.setLook_date(look_date); //작성 시간 넣기
        boardDto.setFk_userid_user_userid((String) httpSession.getAttribute("userid"));
        boardDto.setNickname((String) httpSession.getAttribute("usernickname"));
        boardDto.setLike(1); //좋아요 값넣기 수정해야함
        boardDto.setComment_count(2);//댓글수 수정해야함

        /*
            1. 사용자가 입력한 글내역 중 파일들을 제외하고 나머지 내용들을 입력.
            look 데이터베이스의 글정보가 입력된다.
            !!!20.08.16현재 boardDto에 picture이라는 값이 null로 들어간다 나중에 희진이형꺼합치면서 DB를 대대적으로
            수정할때 제거한다
        */
        boardDao.insert(boardDto);

        /*
            2. 파일리스트들의 내용을 pictureDB에 입력. 위 insert(boardDto) 에서 PK값을 가져온다.
            boardDao.insert()메소드가 실행된후, boardDto에 입력된 look_num을 받는다.
            그 후 pictureDB에 그 값과 함께 값을 넣는다.
            look데이터베이스와 picturedatabase는 다르다!.
        */
        boardDao.insertPicturedatabase(bindingPicture(files, boardDto.getLook_num()));

        /*
            여기서 path는 기본경로/년/월/룩번호를 가지고 있다.
            이제 여기에 사진을 업로드 할 것이다.
         */
        List<String> path = iUtilService.looknumtoPath(boardDto.getLook_num());

        /*
            위 두 insert, insertPicturedatabase를 통해 DB에 글과 글에들어간 사진정보들이 등록됬다.
            3. 이제 mkDir을 통해 기본경로/년/월/룩번호 폴더를 만들다
            그 후 uploadFiles를 통해 파일을 업로드한다.
        */
        iFileService.mkDir(path);
        iFileService.uploadFiles(path,files);
    }

    //글을 지운다.
    @Override
    public int delete(int look_num) {
        logger.info("delete메소드");
        //1. boardDao를 통해. 기본경로/년/월/룩번호를 반환받는다.
        List<String> path = iUtilService.looknumtoPath(look_num);

        //2. FileService의 deleteFiles를 통해 업로드되어있는 사진을 지우겠다.
        //looknumtoAllPicturename 메소드를 통해 룩번호를 매개변수로 주고 해당되는 글의 모든 사진의 이름을 반환받는다.
        List<String> files = iUtilService.looknumtoallPicturename(look_num);
        //deleteallFiles는 폴더 내의 모든 사진을 지운후 look_num에 해당되는 파일도 지운다.

        //deleteallFiles에 매개변수로 룩번호까지의 패스와 지울파일(해당글의 전체파일들)들을 보낸다.
        iFileService.deleteFiles(path, files);
        iFileService.rmDir(path);
        return boardDao.delete(look_num);
    }

    @Override
    public int update(BoardDto boardDto) {
        logger.info("update메소드");
        boardDto.setPicture("사진"); //사진수정 수정해야함
        return boardDao.update(boardDto);
    }

    //Multipart형식으로 들어온 리스트들을 PictureDto리스트로 반환하는 메소드
    //매개변수 looknum은 외래키로 사용할
    @Override
    public ArrayList<PictureDto> bindingPicture(List<MultipartFile> e, int looknum) {
        logger.info("bindingPicture메소드");
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