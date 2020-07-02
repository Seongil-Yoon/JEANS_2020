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
    public String makeDir(String uploadPath,String... paths);
    
    //프로필사진용 썸네일
    //filename은 어차피 개인용 profile이미지는 죄다 profile.jpg 이기 때문에 profile.jpg로 고정된 값을 보낸다.
    //기본이미지인 profile.jpg를 가지고 m_profile.jpg, s_profile.jpg 두개를 만든다.
    //s_는 헤더부분에 작게 들어갈 썸네일이고 m_profile은 개인정보등에 좀 크게 들어할 썸네일이다.
    //uploadPath : 는 application.properties에 설정된 부모업로드 경로
    //그 이후 ...paths는 폴더경로에 맞춰서 사용자 id, profile(고정값) 이라는 매개변수가 입력된다.
    public  void makeprofileThumbnail(String filename,String uploadPath,String... paths) throws Exception;
}
