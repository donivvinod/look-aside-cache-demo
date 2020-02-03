package com.doniv.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LookAsideCacheDemoApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(LookAsideCacheDemoApplication.class, args);
	}

}
