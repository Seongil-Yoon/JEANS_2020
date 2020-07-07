package jeans.ko.Service;

import jeans.ko.Controller.UserController;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public void uploadProfile(String uploadPath,String id,String originalName, byte[] fileData) throws Exception {
        String profilePath=makeDir(uploadPath,id,"profile");

        originalName="profile.jpg";
        //현재 개인 프로파일 사진 전략은 개인당 하나만 저장하는 전략이다.
        //그렇기 때문에 이런식으로 우리가 직접 이름을 지정해줘서 하나만 업로드 되도록하겠다.
        //만약 차후에 프로필사진 여러개를 서버에 저장시키는 방식으로 변경할 때는 이 부분을 지우자.

        File target=new File(profilePath,originalName);
        FileCopyUtils.copy(fileData, target);

   }

    //profile 폴더를 만든후 profile.jpg
    @Override
    public String makeDir(String uploadPath,String... paths){

        logger.info("사진 업로드를 위한 폴더를 만듭니다.");

        //이거 작동안하네????... 나중에 해결하자....
        if(new File(paths[paths.length-1]).exists()){
            logger.info("사진이 업로드 될 폴더가 이미 만들어져있습니다.");
            for(String path:paths){
                uploadPath+="\\"+path;
            }
            logger.info("사진이 업로드될 위치 : "+uploadPath);
            return uploadPath;
        }

        for(String path:paths){
            File dirPath=new File(uploadPath+="\\"+path);
            if(!dirPath.exists()){
                dirPath.mkdir();
            }
        }
        logger.info("폴더를 새로 만들었습니다 : "+uploadPath);
        return uploadPath;
    }

    
    //프로피일용 썸네일을 만드는 메소드
    @Override
    public void makeprofileThumbnail(String filename,String uploadPath,String... paths) throws Exception {

        for(String path:paths){
            uploadPath+="\\"+path;
        }

        //이미지를 읽기 위한 버퍼
        logger.info("썸네일 이미지 생성중");
        logger.info(""+uploadPath);
        File f=new File(uploadPath,filename);

        BufferedImage sourceImg=ImageIO.read(f);
        BufferedImage smallImg= Scalr.resize(sourceImg,Scalr.Method.AUTOMATIC,Scalr.Mode.FIT_TO_WIDTH,40);
        BufferedImage middleImg=Scalr.resize(sourceImg,Scalr.Method.AUTOMATIC,Scalr.Mode.FIT_TO_WIDTH,1000);
        String formatName=filename.substring(filename.lastIndexOf(".")+1);
        File smallThumbnail=new File(uploadPath+"\\"+"s_"+filename);
        File middleThumbnail=new File(uploadPath+"\\"+"m_"+filename);
        ImageIO.write(smallImg,formatName.toUpperCase(),smallThumbnail);
        ImageIO.write(middleImg,formatName.toUpperCase(),middleThumbnail);

        return;
    }

}