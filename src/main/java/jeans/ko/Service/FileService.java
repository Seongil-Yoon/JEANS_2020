package jeans.ko.Service;

import jeans.ko.Controller.UserController;
import jeans.ko.Dao.IUserDao;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class FileService implements IFileService {
    private Logger logger = LoggerFactory.getLogger(FileService.class);

    @Value("${directory}")
    String directory;

    //유저 개인 폴더 밑에 유저의 기본프로필사진&썸네일사진들을 저장하는 파일의 이름이다.
    @Value("${profilepath}")
    String profile;

    //작은 크기썸네일 식별을 위해 원본파일명 앞에 붙이는 식별자다.
    @Value("${profileSmallheader}")
    String smallHeader;

    //중간 크기썸네일 식별을 위해 원본파일명 앞에 붙이는 식별자다.
    @Value("${profileMiddleheader}")
    String middleHeader;

    //위도우에서는 \\, 리눅스서버에서는/
    @Value("${route}")
    String route;

    @Autowired
    IUserDao userDao;

    @Autowired
    IUtilService utilService;

    //썸네일을 만드는 메소드
    @Override
    public void makeprofileThumbnail(List<String> path, MultipartFile file) throws Exception {
        logger.info("makeprofileThumbnail메소드");

        //유저가 입력한 path를 통해 해당유저의 profile폴더까지 찾아간다.
        String completedPath =utilService.plusPath(path) ;
        String filename = file.getOriginalFilename();

        File t = new File(completedPath + route + filename);
        BufferedImage sourceImg = ImageIO.read(t);

        BufferedImage smallImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, 40);
        BufferedImage middleImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, 50);

        String formatName = filename.substring(filename.lastIndexOf(".") + 1);
        File smallThumbnail = new File(completedPath + route + smallHeader + filename);
        File middleThumbnail = new File(completedPath + route + middleHeader + filename);
        ImageIO.write(smallImg, formatName.toUpperCase(), smallThumbnail);
        ImageIO.write(middleImg, formatName.toUpperCase(), middleThumbnail);
        return;
    }

    //폴더생성 : 매개변수 path 리스트로 받은 경로에 폴더를 생성한다.
    @Override
    public String mkDir(List<String> path) {
        logger.info("mkDir메소드");

        String completedPath="";
       //폴더를 만들어줘야하기때문에 utilservice에 있는 pathplus메소드는 사용하지 않는다
        for(String i:path){
            completedPath+=i+route;
            File dirPath=new File(completedPath);
            if(!dirPath.exists()){
                dirPath.mkdir();
                logger.info(completedPath+"폴더가 만들어졌습니다.");
            }
        }
        return completedPath;
    }

    // 파일업로드 : 첫번째 매개변수 path리스트 경로에 두번째 매개변수 files리스트의 파일들을 업로드한다.
    @Override
    public boolean uploadFiles(List<String> path, List<MultipartFile> files) throws Exception {
        logger.info("uploadFiles메소드");
        String completedPath = utilService.plusPath(path);

        for (MultipartFile i : files) {
            byte[] fileData = i.getBytes();
            String name = i.getOriginalFilename();
            File target = new File(completedPath, name);
            FileCopyUtils.copy(fileData, target);
        }
        return true;
    }

    //폴더삭제 : 매개변수 path리스트에 폴더를 삭제한다.
    @Override
    public boolean rmDir(List<String> path) {
        logger.info("rmDir 메소드");

        String completedPath=utilService.plusPath(path);
        new File(completedPath).delete();
        return true;
    }

    //파일 삭제
    @Override
    public void deleteFiles(List<String> path, List<String> files) {
        logger.info("deleteallFiles메소드");
        String completedPath=utilService.plusPath(path);

        for (String i : files) {
            File f = new File(completedPath + route + i);
            if (!f.exists()) {
                logger.info("파일이 존재하지 않습니다.");
                logger.info(f.toString());
                return;
            }
            f.delete();
        }
    }
}
