package jeans.ko.Service;

import jeans.ko.Controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;

@Service
public class FileService implements IFileService {
    private Logger logger = LoggerFactory.getLogger(FileService.class);

    @Override
    public boolean uploadProfile(String uploadPath,String id,String originalName, byte[] fileData) throws Exception {
        String profilePath=makeDir(uploadPath,id,"profile");

        originalName="profile.jpg";
        //현재 개인 프로파일 사진 전략은 개인당 하나만 저장하는 전략이다.
        //그렇기 때문에 이런식으로 우리가 직접 이름을 지정해줘서 하나만 업로드 되도록하겠다.
        //만약 차후에 프로필사진 여러개를 서버에 저장시키는 방식으로 변경할 때는 이 부분을 지우자.

        File target=new File(profilePath,originalName);
        FileCopyUtils.copy(fileData, target);
        return true;
    }

    @Override
    public String makeDir(String uploadPath,String... paths){

        logger.info("사진 업로드를 위한 폴더를 만듭니다.");
       //이거 작동안하네????
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

}
