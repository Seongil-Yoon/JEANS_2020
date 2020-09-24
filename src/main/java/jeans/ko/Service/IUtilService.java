package jeans.ko.Service;

import java.util.List;

public interface IUtilService {
    //looknum을 받아 사진저장경로/년/월/룩번호를 리스트로 반환하는 배열
    public List<String> looknumtoPath(int looknum);

    //looknum을 받아 picture테이블에서 그 looknum을 외래키로 가지고 있는 모든 picture id값을 넘기는 메소드
    public List<String> looknumtoallPicturename(int looknum);

    //유저의 프로필폴더까지의 경로를 리스트로 반환하는 메소드
    public List<String> usertoPath(String userid);

    //String 리스트를 매개변수로 받아 다 합쳐서 경로로 완성시켜준다.
    public String completePath(List<String> paths);
}