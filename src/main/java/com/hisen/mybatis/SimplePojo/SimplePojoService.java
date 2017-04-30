package com.hisen.mybatis.SimplePojo;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SimplePojoService {

  @Resource
  private SimplePojoDao simplePojoDao;

  public int insert(SimplePojo pojo) {
    return simplePojoDao.insert(pojo);
  }

  public int insertList(List<SimplePojo> pojos) {
    return simplePojoDao.insertList(pojos);
  }

  public List<SimplePojo> select(SimplePojo pojo) {
    return simplePojoDao.select(pojo);
  }

  public int update(SimplePojo pojo) {
    return simplePojoDao.update(pojo);
  }

}
