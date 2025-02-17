package com.dangdang.check;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DangdangCheckApplication {

	public static void main(String[] args) {
		SpringApplication.run(DangdangCheckApplication.class, args);
	}

}
