package jeans.ko.Service;

public interface IFileService {
    //단일 파일(프로필사진)업로드
    //uploadPath : 부모 업로드 경로
    //id : 유저의 이름. 유저 이름 파일
    //originalName : 업로드 할 파일명
    //fileData 파일
    public boolean uploadProfile(String uploadPath,String id,String originalName,byte[] fileData) throws Exception;

    //사진폴더 생성
    //uploadPath : 부모 업로드 경로
    //String.... paths  하위경로 파일들
    public String makeDir(String uploadPath,String... paths);

}
