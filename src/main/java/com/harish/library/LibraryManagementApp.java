package com.harish.library;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.harish.library.model.Author;
import com.harish.library.model.Book;
import com.harish.library.repository.AuthorRepository;
import com.harish.library.repository.BookStoreRepository;
import com.harish.library.service.IBookStoreService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EntityScan(basePackages = {"com.harish.library"}) 
public class LibraryManagementApp {
	//978-3-16-148410-0
	@Autowired
	private IBookStoreService bookStoreService;
	
	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BookStoreRepository bookStoreRepository;
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LibraryManagementApp.class, args);
		BookStoreRepository repository = context.getBean(BookStoreRepository.class);
//		b.setIsbn("78-3-16-148410-0");
//		b.setTitle("asdf");
//		b.setAuthor(new Author("harish"));
//		b.setTags(new HashSet());
		
	}
	
	@Bean
	public CommandLineRunner initialCreate() {
		return (args) -> {
			Book book = new Book("978-3-16-148410-0", "Spring in Action");
			Author author = new Author("Harish");
			//authorRepository.save(author);
			book.setAuthor(author);
			bookStoreRepository.save(book);
		};
	}
}