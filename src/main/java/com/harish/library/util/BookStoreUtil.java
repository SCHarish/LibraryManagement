package com.harish.library.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.harish.library.dto.AuthorRequestDto;
import com.harish.library.dto.BookRequestDto;
import com.harish.library.exceptions.InvalidDataException;
import com.harish.library.model.Author;
import com.harish.library.model.Book;
import com.harish.library.model.Tag;

/**
 * 
 * @author harishsc
 *
 */
public class BookStoreUtil {
	/**
	 * 
	 * @param isbn
	 * @return
	 * @throws InvalidDataException
	 */
	public static boolean isValidISBN(String isbn) throws InvalidDataException  {
		if (isbn == null || isbn.isEmpty() || isbn.isBlank()) {
			throw new InvalidDataException("ISBN value cannot be null");
		}
		
		isbn = isbn.replaceAll("-", "");
		int n = isbn.length();
		if (n != 13) {
			return false;
		}
		return true;

	}

	/**
	 * validate book request DTO
	 * @param requestDto
	 * @return
	 * @throws InvalidDataException
	 */
	public static boolean validateBookRequestDto(BookRequestDto requestDto) throws InvalidDataException {
		String isbn = requestDto.getIsbn();
		if (!isValidISBN(isbn)) {
			throw new InvalidDataException("Please provide valid ISBN");
		}
		
		Set<String> tags = new HashSet<String>();
		String[] tags_provided = requestDto.getTags();
		for(String tag : tags_provided) {
			tags.add(tag);
		}
		String[] new_tags = tags.stream().toArray(String[]::new);
		requestDto.setTags(new_tags);
		return true;
	}

	/**
	 * construct Book model object from Book request DTO
	 * @param requestDto
	 * @param author
	 * @param tagList
	 * @return
	 */
	public static Book constructBook(BookRequestDto requestDto, Author author, List<Tag> tagList) {
		Set<Tag> tagSet = new HashSet<Tag>();
		for (Tag tag : tagList) {
			tagSet.add(tag);
		}

		var book = new Book.BookBuilder(requestDto.getIsbn(), requestDto.getTitle()).Author(author).Tags(tagSet)
				.build();
		return book;
	}

	/**
	 * Construct tag model objects from strings
	 * @param tags
	 * @return
	 */
	public static List<Tag> constructTags(List<String> tags) {
		List<Tag> newtagList = new ArrayList();
		for (String tag : tags) {
			var newTag = new Tag();
			newTag.setName(tag);
			newtagList.add(newTag);
		}
		return newtagList;
	}

	/**
	 * 
	 * @param requestDto
	 * @throws InvalidDataException
	 */
	public static void validateAuthorRequestDto(AuthorRequestDto requestDto) throws InvalidDataException {
		String author_name = requestDto.getName();
		if (author_name == null || author_name.isBlank() || author_name.isEmpty())
			throw new InvalidDataException("Author name cannot be null");
	}
}
