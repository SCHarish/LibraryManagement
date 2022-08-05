package com.harish.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

import com.harish.bookstore.model.Book;
import com.harish.bookstore.repository.BookStoreRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EntityScan(basePackages = {"com.harish.bookstore"}) 
public class LibraryManagementApp {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LibraryManagementApp.class, args);
		BookStoreRepository repository = context.getBean(BookStoreRepository.class);
		Book b1 = new Book("234", "asasdf");
		repository.save(b1);	
	}

}