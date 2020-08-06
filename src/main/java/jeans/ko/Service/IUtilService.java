package jeans.ko.Service;

import java.util.List;

public interface IUtilService {
    //looknum을 받아 /년/월/룩번호를 리스트로 반환하는 배열
    public List<String> looknumtoPath(int looknum);

    //looknum을 받아 picture테이블에서 그 looknum을 외래키로 가지고 있는 모든 picture id값을 넘기는 메소드
    public List<String> looknumtoallPicturename(int looknum);

}
