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
    IUtilService utilService;

    @Autowired
    IUserDao userDao;

    //폴더생성
    @Override
    public void mkDir(List<String> paths) {
        logger.info("mkDir메소드()");
        String completePath = "";
        for (String path : paths) {
            File dirPath = new File(completePath += path + route);
            if (!dirPath.exists()) {
                dirPath.mkdir();
            }
        }
        return;
    }

    //프로필썸네일이미지 생성
    @Override
    public boolean mkProfilethumbnail(List<String> paths, String userid) throws Exception {
        logger.info("mkProfilethumbnail메소드");
        String path = utilService.completePath(paths);
        String picture = userDao.getPicture(userid);
        File f = new File(path, picture);
        if (!f.exists()) {
            logger.info("썸네일이 없습니다");
            return false;
        }
        BufferedImage sourceImg = ImageIO.read(f);


        int length = 50;//50의 정사각형으로 썸네일을 생성한다.
        int ow = sourceImg.getWidth();
        int oh = sourceImg.getHeight();
        int nw,nh;

        if (ow < oh) {
            nw = ow;
            nh = ow;
        } else {
            nw = oh;
            nh = oh;
        }
        BufferedImage cropImg=Scalr.crop(sourceImg,(ow-nw)/2,(oh-nh)/2,nw,nh);
        BufferedImage smallImg = Scalr.resize(cropImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, 40);
        BufferedImage middleImg = Scalr.resize(cropImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, 50);

      /*  BufferedImage smallImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, 40);
        BufferedImage middleImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, 50);*/

        String formatName = picture.substring(picture.lastIndexOf(".") + 1);
        File smallThumbnail = new File(path + route + smallHeader + picture);
        File middleThumbnail = new File(path + route + middleHeader + picture);
        ImageIO.write(smallImg, formatName.toUpperCase(), smallThumbnail);
        ImageIO.write(middleImg, formatName.toUpperCase(), middleThumbnail);
        return true;
    }

    //파일업로드
    @Override
    public boolean uploadFiles(List<String> paths, List<MultipartFile> files) throws Exception {
        logger.info("uploadFiles메소드()");
        String path = utilService.completePath(paths);
        File dirPath = new File(path);
        if (!dirPath.exists()) {
            logger.info("uploadFiles메소드 : 업로드 할 폴더가 없습니다!!!");
            return false;
        } else {
            for (MultipartFile file : files) {
                File target = new File(path, file.getOriginalFilename());
                FileCopyUtils.copy(file.getBytes(), target);
            }
            return true;
        }
    }

    //파일제거
    @Override
    public boolean rmFiles(List<String> paths, List<String> pictures) {
        logger.info("rmFiles메소드");
        String path = utilService.completePath(paths);
        File f = new File(path);
        if (!f.exists()) {
            logger.info("파일을 제거할 경로가 없습니다");
            logger.info(path + "가 존재하지 않음");
            return false;
        }
        for (String picture : pictures) {
            new File(path + picture).delete();
        }
        return true;
    }

    //폴더삭제
    @Override
    public boolean rmDir(List<String> pahts) {
        logger.info("rmDir메소드");
        String path = utilService.completePath(pahts);
        File f = new File(path);
        if (!f.exists()) {
            logger.info("삭제할 폴더가 이미 없습니다");
            return true;
        }
        f.delete();
        return true;
    }
}
