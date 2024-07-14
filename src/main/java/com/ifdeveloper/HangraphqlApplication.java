package com.ifdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class HangraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(HangraphqlApplication.class, args);
	}

}
