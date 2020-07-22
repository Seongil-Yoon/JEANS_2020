package jeans.ko.Service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileService {
    //단일 파일(프로필사진)업로드
    //uploadPath : 부모 업로드 경로
    //id : 유저의 이름. 유저 이름 파일
    //originalName : 업로드 할 파일명
    //fileData 파일
    public void uploadProfile(String uploadPath,String id,String originalName,byte[] fileData) throws Exception;

    //사진폴더 생성
    //uploadPath : 부모 업로드 경로
    //String.... paths  하위경로 파일들
    //makedir은 uploadPath부터 차례차례 폴더로 쓸 명을 매개변수로 받아 폴더를 만든다.
    public String makeDir(String uploadPath,String... paths);

    //프로필사진용 썸네일
    //makeDir과 비슷하게 받은 매개변수를 통해 유저가 올린 이미지를 찾는다.
    //기본이미지를 가지고 m_이미지명, s_이미지명 두개를 만든다.
    //s_이미지명은 헤더부분에 작게 들어갈 썸네일이고 m_이미지명 은 글목록 등 좀 크게 들어할 썸네일이다.
    //uploadPath : 는 application.properties에 설정된 부모업로드 경로
    //그 이후 ...paths는 폴더경로에 맞춰서 사용자 id, 이미지명이 매개변수로 입력된다.
    public  void makeprofileThumbnail(String filename,String uploadPath,String... paths) throws Exception;

    //이미지 폴더일 경우에는 유저의 개인프로필사진과는 경로가 다르다.
    //경로 : ${directory}/년/월/ 이 경로를 기본으로 폴더를 만든다..
    public String makepictureDir(List<String> lists);

    //makepictureDir에서 패스를 매개변수로 받는다. 동시에 boardDto에서 lookNum을 받아 경로를 완성한다.
    //완성된 경로는 ${directory}/년/월/num(글의 pk값)이다.
    //files는 유저가 업로드한 파일리스트이다.
    public void uploadPictures(List<MultipartFile> files,String path)throws Exception;

    //사진 삭제 메소드 path는 룩번호폴더까지의 경로, files는 삭제할 파일들의 이름.
    public void deleteFiles(List<String>path,List<String>files);

    //사진 전체 삭제 메소드 path까지 룩번호폴더까지경로,files는 삭제할 파일들의 이름. 그후 look_num의 파일을 제거한다
    //디렉터리를 지울때 안에 파일이 하나라도 남아있을 경우 delete 메소드가 작동하지 않는다.
    //나머지는 deleteFiles 메소드와 같다.
    public void deleteallFiles(List<String>path,List<String>files);
}
