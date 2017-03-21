package com.hisen.log4j.log4j2MySQL;

import org.apache.log4j.Logger;

/**
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
    }
}
