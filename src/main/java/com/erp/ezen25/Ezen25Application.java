package com.erp.ezen25;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Ezen25Application {

	public static void main(String[] args) {
		SpringApplication.run(Ezen25Application.class, args);
	}

}

