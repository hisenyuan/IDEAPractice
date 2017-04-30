package com.hisen.jars.log4j;

import org.apache.log4j.Logger;

public class Test {

  private static Logger logger = Logger.getLogger(Test.class);

  public static void main(String[] args) {
    logger.debug("This is debug message");
    logger.info("This is info message");
    logger.error("This is error message");
  }
}
