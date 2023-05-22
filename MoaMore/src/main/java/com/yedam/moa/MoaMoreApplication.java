package com.yedam.moa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@EnableScheduling	// 스케쥴러
@SpringBootApplication
@EnableJpaRepositories
@MapperScan(basePackages="com.yedam.moa.**.mapper")
@Controller
public class MoaMoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoaMoreApplication.class, args);
	}
	
	@GetMapping("/login")
	public String hello() {
		return "loginForm";
	}		
	@GetMapping("/main")
	public String home() {
		return "main";
	}
	@GetMapping("/vueAdmin")
	public String vueTest() {
		return "admin";
	}
	
	
}
