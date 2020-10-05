package jeans.ko.Service;

import jeans.ko.Controller.UserController;
import jeans.ko.Dao.IBoardDao;
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
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

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

    @Autowired
    IBoardDao boardDao;

    //폴더생성
    @Override
    public void mkDir(List<String> paths) {

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

        String path = utilService.completePath(paths);
        String picture = userDao.getPicture(userid);
        File f = new File(path, picture);
        if (!f.exists()) {

            return false;
        }
        BufferedImage sourceImg = ImageIO.read(f);


        int length = 50;//50의 정사각형으로 썸네일을 생성한다.
        int ow = sourceImg.getWidth();
        int oh = sourceImg.getHeight();
        int nw, nh;

        if (ow < oh) {
            nw = ow;
            nh = ow;
        } else {
            nw = oh;
            nh = oh;
        }
        BufferedImage cropImg = Scalr.crop(sourceImg, (ow - nw) / 2, (oh - nh) / 2, nw, nh);
        BufferedImage smallImg = Scalr.resize(cropImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, 40);
        BufferedImage middleImg = Scalr.resize(cropImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, 50);

        String formatName = picture.substring(picture.lastIndexOf(".") + 1);
        File smallThumbnail = new File(path + route + smallHeader + picture);
        File middleThumbnail = new File(path + route + middleHeader + picture);
        ImageIO.write(smallImg, formatName.toUpperCase(), smallThumbnail);
        ImageIO.write(middleImg, formatName.toUpperCase(), middleThumbnail);
        return true;
    }

    @Override
    public boolean mkBoardthumbnail(List<String> paths) throws Exception {

        String path = utilService.completePath(paths);
        int looknum = Integer.parseInt(paths.get(paths.size() - 1));
        List<String> pictures = utilService.looknumtoallPicturename(looknum);
        for(String i:pictures){
            File f = new File(path, i);
            String formatName = i.substring(i.lastIndexOf(".") + 1);
            BufferedImage sourceImage = ImageIO.read(f);
            BufferedImage resizedImage=Scalr.resize(sourceImage,Scalr.Method.AUTOMATIC,Scalr.Mode.FIT_TO_HEIGHT,500);
            ImageIO.write(resizedImage,formatName,f);
        }
        return true;
    }


    //파일업로드
    @Override
    public boolean uploadFiles(List<String> paths, List<MultipartFile> files) throws Exception {

        String path = utilService.completePath(paths);
        String boardnum=paths.get(paths.size()-1);//글 번호

        for (MultipartFile file : files) {
            UUID uuid=UUID.randomUUID();
            byte[] fileData=file.getBytes();
            //String name=file.getOriginalFilename();
            File target = new File(path, uuid.toString().concat(".jpg"));

                /*
                    예전에는 insertPicturedatabase를 리스트형식으로 picture데이터베이스에 넣었다면
                    지금은 UUID때문에 다 따로 넣어야한다.
                */
            boardDao.insertPicture(boardnum,uuid.toString().concat(".jpg"));
            FileCopyUtils.copy(fileData, target);
        }
        return true;
    }

    @Override
    public void uploadFile(List<String> paths, MultipartFile file) throws Exception {

        String path=utilService.completePath(paths);
        byte[] fileData=file.getBytes();
        String name=file.getOriginalFilename();
        File target=new File(path,name);
        FileCopyUtils.copy(fileData,target);
    }

    //파일 삭제
    @Override
    public void rmFiles(List<String> path, List<String> files) {

        String completedPath=utilService.completePath(path);

        for (String i : files) {
            File f = new File(completedPath + route + i);
            if (!f.exists()) {

                return;
            }
            f.delete();
        }
    }

    //폴더삭제
    @Override
    public boolean rmDir(List<String> pahts) {

        String path = utilService.completePath(pahts);
        File f = new File(path);
        if (!f.exists()) {

            return true;
        }
        f.delete();
        return true;
    }
}