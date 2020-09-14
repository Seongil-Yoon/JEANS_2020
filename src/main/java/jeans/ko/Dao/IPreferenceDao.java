package jeans.ko.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface IPreferenceDao {
    public void insertPrefer(int looknum,String userid);

    //룩번호와 유저아이디를 매개변수로 받아 좋아요처리가 되있느지 아닌지 여부확인
    public int getUser(int looknum,String userid);
}
