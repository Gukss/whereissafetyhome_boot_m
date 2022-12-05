package com.ssafy.happyhouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication//(exclude={DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.ssafy.happyhouse"})
public class WhereismyhomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhereismyhomeApplication.class, args);
	}

}
