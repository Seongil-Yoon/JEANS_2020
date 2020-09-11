package jeans.ko.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface IPreferenceDao {
    public void insertPrefer(int looknum,String userid);
}
