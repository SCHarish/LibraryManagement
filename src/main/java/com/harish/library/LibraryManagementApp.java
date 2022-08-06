package com.harish.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

import com.harish.library.model.Book;
import com.harish.library.repository.BookStoreRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EntityScan(basePackages = {"com.harish.library"}) 
public class LibraryManagementApp {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LibraryManagementApp.class, args);
		BookStoreRepository repository = context.getBean(BookStoreRepository.class);
		Book b1 = new Book("978-3-16-148410-0", "asasdf");
		repository.save(b1);	
	}

}