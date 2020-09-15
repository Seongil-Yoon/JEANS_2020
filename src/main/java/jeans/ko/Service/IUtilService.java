package jeans.ko.Service;

import java.util.ArrayList;
import java.util.List;

public interface IUtilService {
    //looknum을 받아 /년/월/룩번호를 리스트로 반환하는 배열
    public List<String> looknumtoPath(int looknum);

    //looknum을 받아 picture테이블에서 그 looknum을 외래키로 가지고 있는 모든 picture id값을 넘기는 메소드
    public List<String> looknumtoallPicturename(int looknum);

    // 유저의 profile경로반환 : id를 받아 해당사용자의 profile 폴더까지의경로를 반환한다.
    public ArrayList<String> getProfilepath(String userid);

    //paths 배열을 받아 다 합쳐서 경로로 반환해준다.
    public String plusPath(List<String>paths);

}
