package com.hrong.major;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 1011486768
 */
@SpringBootApplication
@MapperScan("com.hrong.major.dao")
public class MajorShowApplication {

	public static void main(String[] args) {
		SpringApplication.run(MajorShowApplication.class, args);
	}

}
