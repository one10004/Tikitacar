package com.bigdata.tikitacar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class TikitacarApplication {

	public static void main(String[] args) {
		SpringApplication.run(TikitacarApplication.class, args);
	}

}
