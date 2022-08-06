package com.harish.library.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.harish.library.dto.RequestDto;
import com.harish.library.exceptions.BookNotFoundException;
import com.harish.library.model.Book;
import com.harish.library.repository.BookStoreRepository;
import com.harish.library.service.impl.BookStoreServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BookStoreServiceImplTest {

	@Mock
    private BookStoreRepository bookRepository;
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BookStoreServiceImpl bookStoreService;

//    @Test
//    public void testAddNewBook() {
//        //Arrange
//        BookDto bookDto = mock(BookDto.class);
//        Book book = mock(Book.class);
//        when(bookDto.getIsbn()).thenReturn(id);
//        when(bookRepository.findById(id)).thenReturn(Optional.empty());
//        when(modelMapper.map(bookDto, Book.class)).thenReturn(book);
//
//        //Act
//        bookStoreService.addBook(bookDto);
//
//        //Verify
//        verify(bookRepository).save(book);
//    }

}
