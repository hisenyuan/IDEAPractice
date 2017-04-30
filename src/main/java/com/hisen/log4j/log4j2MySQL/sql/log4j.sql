#创建数据库
CREATE DATABASE log4j;
#使用
USE log4j;
#建表
CREATE TABLE log (
  Class      VARCHAR(255) NULL,
  Mothod     VARCHAR(255) NULL,
  CreateTime VARCHAR(255) NULL,
  LogLevel   VARCHAR(20)  NULL,
  MSG        VARCHAR(555) NULL
)