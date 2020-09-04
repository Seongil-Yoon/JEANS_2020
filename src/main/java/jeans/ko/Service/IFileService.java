package jeans.ko.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;

public interface IFileService {
    //폴더생성 : String 리스트로 받은 항목을 폴더로 만든다.
    public void mkDir(List<String>paths);

    //프로필썸네일 생성 : paths리스트에 userid에 해당되는 프로필 사진을 이용하여 그 프로필사진을 축소하여 썸네일로 만든다
    public boolean mkProfilethumbnail(List<String>paths,String userid) throws Exception;

    //글에서 쓰일 썸네일 생성
    public boolean mkBoardthumbnail(List<String>paths)throws Exception;

    //파일 업로드 : paths리스트 경로에 files리스트들을 업로드한다
    public boolean uploadFiles(List<String>paths,List<MultipartFile>files)throws Exception;

//    public void uploadFile(List<String>paths,MultipartFile file)throws Exception;

    //파일삭제 paths리스트에 있는 pictures리스트들을 삭제한다.
    public void rmFiles(List<String>paths,List<String>pictures);

    //폴더 삭제 : 경로의 폴더를 삭제한다.
    public boolean rmDir(List<String>pahts);



}
