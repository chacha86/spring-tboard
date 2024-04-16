package com.example.tboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(TboardApplication.class, args);
	}

}
