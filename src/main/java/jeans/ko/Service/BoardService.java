package jeans.ko.Service;

import jeans.ko.Controller.UserController;
import jeans.ko.Dao.IBoardDao;
import jeans.ko.Dto.BoardDto;
import jeans.ko.Dto.MoodDto;
import jeans.ko.Dto.PictureDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
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

    public void insert(BoardDto boardDto, List<MoodDto> moodDtos, List<MultipartFile> files) throws Exception {
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


        //1. 사용자가 입력한 글내역 중 파일들을 제외하고 나머지 내용들을 입력.
        //여기서 look 데이터베이스에 정보가 입력된다.
        boardDao.insert(boardDto);

        /*
            더 이상 사용되지 않는다. 그전에는 이런식으로 픽쳐테이블에 bindingPicture메소드를 통해 List로 insert 시켰지만,
            글 수정 시 이미지 명이 다 동일하게 바뀌는 문제때문에 더 이상 사용되지 않는다.
            아까워서 남겨뒀다.
            boardDao.insertPicturedatabase(bindingPicture(files, boardDto.getLook_num()));
        */
        //만약 유저가 해당 글의 무드를 선택했다면,
        if (moodDtos != null) {
            for (int i = 0; i < moodDtos.size(); i++) {
                //무드 리스트의 경우 현재 look의 넘버, 글의 id 값이 없어서 이런식으로 다 넣어줘야한다.
                (moodDtos.get(i)).setLook_num(boardDto.getLook_num());
            }
            //무드데이터베이스에 해당글의 무드를 선택해야한다.
            boardDao.insertMooddatabase(moodDtos);
        }

        //여기서... 이제 년/월/룩번호를 뺏다
        List<String> path = iUtilService.looknumtoPath(boardDto.getLook_num());
        //3.makepictureDir을 통해 ${directory}/년/월/룩번호 까지 폴더를 생성한다.
        //uploadPictures에 파일리스트, makepictureDir을 통해 만든 경로, 글의 pk looknum을 매개변수로 입력한다.
        //${directory}/년/월/looknum(pk값) 안에 이미지가 업로드 된다.
        //  String pathPicture = iFileService.makepictureDir(path);
        iFileService.mkDir(path);
        //iFileService.uploadPictures(files, pathPicture);
        iFileService.uploadFiles(path, files);
        //  iFileService.mkBoardthumbnail(path);
    }

    //글을 지운다.
    @Override
    public int delete(int look_num) {
        logger.info("delete메소드");
        //1. boardDao를 통해. 년/월/룩번호를 반환받는다.
        List<String> path = iUtilService.looknumtoPath(look_num);

        //2. FileService의 deleteFiles를 통해 업로드되어있는 사진을 지우겠다.
        //looknumtoAllPicturename 메소드를 통해 룩번호를 매개변수로 주고 해당되는 글의 모든 사진의 이름을 반환받는다.
        List<String> files = iUtilService.looknumtoallPicturename(look_num);
        //deleteallFiles는 폴더 내의 모든 사진을 지운후 look_num에 해당되는 파일도 지운다.
        //deleteallFiles에 매개변수로 룩번호까지의 패스와 지울파일(해당글의 전체파일들)들을 보낸다.
        iFileService.rmFiles(path, files);
        iFileService.rmDir(path);
        return boardDao.delete(look_num);
    }

    @Override
    public int update(BoardDto boardDto, List<MultipartFile> files) throws Exception {
        logger.info("update메소드");
        //폴더내 파일 삭제
        List<String> path = iUtilService.looknumtoPath(boardDto.getLook_num());
        System.out.println("path = " + path);

        System.out.println(iUtilService.looknumtoallPicturename(boardDto.getLook_num()));
        iFileService.rmFiles(path, iUtilService.looknumtoallPicturename(boardDto.getLook_num()));
        //DB내 해당 룩 번호 관여 제거
        boardDao.deleteAllpictures(boardDto.getLook_num());

        //신규사진 picture DB내 insert
        boardDao.insertPicturedatabase(bindingPicture(files, boardDto.getLook_num()));
        //신규사진 업로드
        iFileService.uploadFiles(path, files);

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
            pictureDto.setLookNum(looknum);
            pictureDto.setPictureName(name);
            lists.add(pictureDto);
        }

        return lists;
    }
}