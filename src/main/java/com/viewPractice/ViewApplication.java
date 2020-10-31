package com.viewPractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//继承SpringBootServletInitializer类
public class ViewApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ViewApplication.class, args);
		System.out.println("启动成功");
	}
	/**
	 *新增此方法
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 注意这里要指向原先用main方法执行的Application启动类
		return builder.sources(ViewApplication .class);
	}
}


