package com.hisen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用程序启动引导类，也是主要的Spring配置类
 */
/**
 * 开启组件扫描和自动配置 其实包含了三个注解
 * @Configuration
 * @ComponentScan
 * @EnableAutoConfiguration
 */
@SpringBootApplication
public class ReadinglistApplication {

	public static void main(String[] args) {
		//负责启动引导应用程序
		SpringApplication.run(ReadinglistApplication.class, args);
	}
}
