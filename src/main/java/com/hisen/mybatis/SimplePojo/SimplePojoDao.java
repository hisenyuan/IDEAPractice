package com.hisen.mybatis.SimplePojo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SimplePojoDao {

  int insert(@Param("pojo") SimplePojo pojo);

  int insertList(@Param("pojos") List<SimplePojo> pojo);

  List<SimplePojo> select(@Param("pojo") SimplePojo pojo);

  int update(@Param("pojo") SimplePojo pojo);

}
