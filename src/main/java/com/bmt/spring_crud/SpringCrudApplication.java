package com.bmt.spring_crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com/bmt/spring_crud/controller", "com/bmt/spring_crud/repository"})
@EntityScan(basePackages = {"com/bmt/spring_crud/entity"})
public class SpringCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCrudApplication.class, args);
		System.out.println("RUNNING");
	}

}
