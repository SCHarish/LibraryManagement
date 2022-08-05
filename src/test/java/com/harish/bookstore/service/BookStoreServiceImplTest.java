package com.harish.bookstore.service;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.harish.bookstore.repository.BookStoreRepository;

@RunWith(MockitoJUnitRunner.class)
public class BookStoreServiceImplTest {

	@Mock
    private BookStoreRepository bookRepository;
}
