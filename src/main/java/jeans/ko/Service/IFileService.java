package jeans.ko.Service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileService {

    /*
        프로필사진용 썸네일 생성메소드
        기본이미지를 가지고 m_이미지명, s_이미지명 두개를 만든다.
        s_이미지명은 헤더부분에 작게 들어갈 썸네일이고 m_이미지명 은 글목록 등 좀 크게 들어할 썸네일이다.
        첫번째 매개변수 path는 업로드 될 폴더, file 업로드할 프로필 썸네일 이미지
    */
    public void makeprofileThumbnail(List<String>path, MultipartFile file) throws Exception;

    // 폴더생성 : 매개변수 path 리스트로 받은 경로에 폴더를 생성한다.
    public String mkDir(List<String> path);

    // 파일업로드 : 첫번째 매개변수 path리스트 경로에 두번째 매개변수 files리스트를 업로드한다.
    public boolean uploadFiles(List<String> path, List<MultipartFile> files) throws Exception;

    // 폴더삭제 : 매개변수 path리스트에 폴더를 삭제한다.
    public boolean rmDir(List<String> path);

    // 파일삭제 : 첫번째매개변수 path 리스트는 경로, 두번째매개변수리스트는 삭제할 파일들이다.
     public void deleteFiles(List<String> path, List<String> files);

}
