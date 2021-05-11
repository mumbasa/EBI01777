package com.shrinq.entry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan("com.*")


@Configuration
public class Ebi01777Application {

	public static void main(String[] args) {
		SpringApplication.run(Ebi01777Application.class, args);
	}

}
