package com.hisen.mybatis.SimplePojo;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.hisen.mybatis.SimplePojo.SimplePojo;

public interface SimplePojoDao {

    int insert(@Param("pojo") SimplePojo pojo);

    int insertList(@Param("pojos") List< SimplePojo> pojo);

    List<SimplePojo> select(@Param("pojo") SimplePojo pojo);

    int update(@Param("pojo") SimplePojo pojo);

}
