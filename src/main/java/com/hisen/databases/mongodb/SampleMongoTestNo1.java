package com.hisen.databases.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import java.util.regex.Pattern;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * mongodb之简单的CRUD
 * Created by hisenyuan on 2017/7/11 at 19:11.
 */
public class SampleMongoTestNo1 {
  private Mongo mg = null;
  private DB db;
  private DBCollection dbCollection;

  @Before
  public void initDB(){
    //建立连接
    mg = new MongoClient("127.0.0.1",27017);
    //获取要操作的数据库实例，没有会创建
    db = mg.getDB("hisen");
    //获取要操作的集合实例，没有会创建
    dbCollection = db.getCollection("emp");
  }

  /**
   * 插入数据
   */
  @Test
  public void testCreate(){
    DBObject obj = null;
    for (int i = 1; i <= 100000; i++) {
      obj = new BasicDBObject("_id",i).append("name","hisen"+i).append("age",i*5);
      dbCollection.save(obj);
    }
  }

  /**
   * 查询所有
   */
  @Test
  public void testReadAll(){
    DBCursor dbCursor = dbCollection.find();
    while (dbCursor.hasNext()){
      System.out.println(dbCursor.next());
    }
  }

  /**
   * 修改记录
   */
  @Test
  public void testUpdate(){
    BasicDBObject condition = new BasicDBObject("_id",10);
    BasicDBObject res = new BasicDBObject("name", "hisen10_new");
    //若没有此语句，直接调用下面的语句，返回结果{ "_id" : 10 , "name" : "hisen10_new"}
    BasicDBObject res2 = new BasicDBObject("$set", res);
    dbCollection.update(condition,res2);
    System.out.println(dbCollection.findOne(new BasicDBObject("_id",10)));
  }

  /**
   * 删除记录
   */
  @Test
  public void testDelete(){
    dbCollection.remove(new BasicDBObject("_id",10));
    testReadAll();
  }

  /**
   * 根据主键查询
   */
  @Test
  public void testReadOneWithId(){
    DBObject object = dbCollection.findOne(new BasicDBObject("_id",1));
    System.out.println(object);
  }

  /**
   * 模糊查询 - 使用正则
   */
  @Test
  public void testReadPuzzy(){
    Pattern pattern = Pattern.compile("^hisen1");
    BasicDBObject basicDBObject = new BasicDBObject("name",pattern);
    DBCursor dbCursor = dbCollection.find(basicDBObject);
    while (dbCursor.hasNext()){
      System.out.println(dbCursor.next());
    }
  }

  /**
   * 清空集合
   */
  @Test
  public void testDrop(){
    dbCollection.drop();
    testReadAll();
  }
  @After
  public void destoryDB(){
    if (mg == null) {
      mg.close();
      mg=null;
      db=null;
      dbCollection=null;
    }
  }
}
