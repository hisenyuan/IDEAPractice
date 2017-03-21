#创建数据库
CREATE database log4j;
#使用
use log4j;
#建表
CREATE TABLE log(
    Class varchar (255)   NULL ,
    Mothod varchar (255)   NULL ,
    CreateTime varchar (255)   NULL ,
    LogLevel varchar (20)   NULL ,
    MSG varchar (555)   NULL
)