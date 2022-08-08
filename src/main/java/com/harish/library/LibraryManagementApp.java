package com.harish.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EntityScan(basePackages = { "com.harish.library" })
public class LibraryManagementApp {
	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApp.class, args);
	}
}