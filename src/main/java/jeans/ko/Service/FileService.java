package jeans.ko.Service;

import jeans.ko.Controller.UserController;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;

@Service
public class FileService implements IFileService {
    private Logger logger = LoggerFactory.getLogger(FileService.class);

    //유저 개인 폴더 밑에 유저의 기본프로필사진&썸네일사진들을 저장하는 파일의 이름이다.
    @Value("${profilepath}")
    String profile;

    //작은 크기썸네일 식별을 위해 원본파일명 앞에 붙이는 식별자다.
    @Value("${profileSmallheader}")
    String smallHeader;

    //중간 크기썸네일 식별을 위해 원본파일명 앞에 붙이는 식별자다.
    @Value("${profileMiddleheader}")
    String middleHeader;

    @Override
    public void uploadProfile(String uploadPath, String id, String originalName, byte[] fileData) throws Exception {
        //uploadPath : application.properties에 지정된 기본 이미지 저장경로
        //id : 유저의 id 각각유저의 폴더
        //originalName : 이미지파일 원본 명
        //fileData : 파일 byte
        //makeDir은 폴더명들을 매개변수로 받아 폴더를 만든다.
        //여기서 makeDir에 매개변수로, uploadPath,id,profile을 준다.
        //그러면 makeDir은 uploadPath파일 밑에 받은 id로 폴더를 만들고 또 profile이라는 폴더를 만든다.
        String profilePath = makeDir(uploadPath, id, profile);
        //makeDir은 그후 /uploadPath/유저의id/profile이라는 path를 반환한다.

        File target = new File(profilePath, originalName);
        FileCopyUtils.copy(fileData, target);
    }

    //makeDir은 uploadPath를 받고 ...을 통해 여러 path들을 받는다.
    //makeDir은 받은 path를 가지고 폴더를 만든다.
    @Override
    public String makeDir(String uploadPath, String... paths) {

        logger.info("사진 업로드를 위한 폴더를 만듭니다.");


        for (String path : paths) {
            //uploadPath 뒤로 매개변수로 입력받은 path들을 다 붙여준다.
            File dirPath = new File(uploadPath += "\\" + path);
            if (!dirPath.exists()) {
                //만약 폴더가 존재하지 않는다면 경로에 해당되는 폴더를 만든다.
                dirPath.mkdir();
            }
        }
        logger.info("폴더를 새로 만들었습니다 : " + uploadPath);
        return uploadPath;
    }


    //썸네일을 만드는 메소드
    //입력된 매개변수로 유저가 올린 이미지 파일을 찾는다.
    @Override
    public void makeprofileThumbnail(String filename, String uploadPath, String... paths) throws Exception {

        //유저가 입력한 path를 통해 해당유저의 profile폴더까지 찾아 들어간다.
        for (String path : paths) {
            uploadPath += "\\" + path;
        }

        //이미지를 읽기 위한 버퍼
        logger.info("썸네일 이미지 읽어들이는 중");
        //위에서 찾은 해당유저의 profile경로와 filename 즉 이미지 파일명을 통해
        File f = new File(uploadPath, filename);

        BufferedImage sourceImg = ImageIO.read(f);
        BufferedImage smallImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, 40);
        BufferedImage middleImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, 50);
        String formatName = filename.substring(filename.lastIndexOf(".") + 1);
        File smallThumbnail = new File(uploadPath + "\\" + smallHeader + filename);
        File middleThumbnail = new File(uploadPath + "\\" + middleHeader + filename);
        ImageIO.write(smallImg, formatName.toUpperCase(), smallThumbnail);
        ImageIO.write(middleImg, formatName.toUpperCase(), middleThumbnail);

        return;
    }

}
