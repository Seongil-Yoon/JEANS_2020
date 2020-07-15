package jeans.ko.Service;

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
}
