package com.hisen.log4j.log4j2MySQL;

import org.apache.log4j.Logger;

/**
 * 测试一下log4j把日志插入到mysql数据库
 * 插入语句和数据库配置在log4j的配置文件中
 * Created by hisenyuan on 2017/3/21 at 14:23.
 */
public class Log4jDemo {
    private static Logger logger = Logger.getLogger(Log4jDemo.class);

    public static void main(String[] args) {
        logger.debug("这是debug信息");
        // 记录info级别的信息
        logger.info("这是info信息");
        // 记录error级别的信息
        logger.error("这是error信息");
        hisen();
    }

    public static void hisen() {
        logger.debug("这是方法中debug信息");
        // 记录info级别的信息
        logger.info("这是方法中info信息");
        // 记录error级别的信息
        logger.error("这是方法中error信息");
    }
/**
 * mysql中的数据
mysql> select * from log;
+---------------------------------------+--------+---------------------+----------+------------------------+
| Class                                 | Mothod | CreateTime          | LogLevel | MSG                    |
+---------------------------------------+--------+---------------------+----------+------------------------+
| com.hisen.log4j.Log4jDemo             | main   | 2017-03-21 12:49:10 | INFO     | This is info message1. |
| com.hisen.log4j.Log4jDemo             | main   | 2017-03-21 12:49:11 | ERROR    | This is error message. |
| com.hisen.log4j.Log4jDemo             | main   | 2017-03-21 12:54:34 | INFO     | This is info message1. |
| com.hisen.log4j.Log4jDemo             | main   | 2017-03-21 12:54:34 | ERROR    | This is error message. |
| com.hisen.log4j.log4jMySQL.Log4jDemo  | main   | 2017-03-21 13:36:27 | INFO     | 这是info信息           |
| com.hisen.log4j.log4jMySQL.Log4jDemo  | main   | 2017-03-21 13:36:28 | ERROR    | 这是error信息          |
| com.hisen.log4j.log4j2MySQL.Log4jDemo | main   | 2017-03-21 14:27:16 | INFO     | 这是info信息           |
| com.hisen.log4j.log4j2MySQL.Log4jDemo | main   | 2017-03-21 14:27:16 | ERROR    | 这是error信息          |
| com.hisen.log4j.log4j2MySQL.Log4jDemo | main   | 2017-03-21 14:39:34 | INFO     | 这是info信息           |
| com.hisen.log4j.log4j2MySQL.Log4jDemo | main   | 2017-03-21 14:39:34 | ERROR    | 这是error信息          |
| com.hisen.log4j.log4j2MySQL.Log4jDemo | main   | 2017-03-21 14:42:27 | INFO     | 这是info信息           |
| com.hisen.log4j.log4j2MySQL.Log4jDemo | main   | 2017-03-21 14:42:27 | ERROR    | 这是error信息          |
| com.hisen.log4j.log4j2MySQL.Log4jDemo | hisen  | 2017-03-21 14:42:27 | INFO     | 这是方法中info信息     |
| com.hisen.log4j.log4j2MySQL.Log4jDemo | hisen  | 2017-03-21 14:42:27 | ERROR    | 这是方法中error信息    |
+---------------------------------------+--------+---------------------+----------+------------------------+
14 rows in set
*/
}
