package com.springboot.racemanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.springboot.racemanage.dao")
public class RacemanageApplication {

	public static void main(String[] args) {
		SpringApplication.run(RacemanageApplication.class, args);
	}
}
