package com.angularBootRef.springBootPortfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBootPortfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPortfolioApplication.class, args);
	}
}
