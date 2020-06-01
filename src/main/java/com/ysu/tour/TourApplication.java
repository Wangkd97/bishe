package com.ysu.tour;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling   // 开启对定时任务的支持
@SpringBootApplication
@MapperScan("com.ysu.tour.dao")
public class TourApplication {

	public static void main(String[] args) {

		SpringApplication.run(TourApplication.class, args);
	}


}

