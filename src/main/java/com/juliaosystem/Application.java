package com.juliaosystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.common.lib"})
@EnableJpaRepositories(basePackages = {"com.common.lib.infraestructure.repository" ,"com.juliaosystem.infraestructure.repository"})
@EntityScan(basePackages = {"com.common.lib.infraestructure.entitis" , "com.juliaosystem.infraestructure.entitis"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
