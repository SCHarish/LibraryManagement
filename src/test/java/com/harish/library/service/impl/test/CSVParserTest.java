package com.harish.library.service.impl.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import com.harish.library.dto.BookRequestDto;
import com.harish.library.exceptions.InvalidFileException;
import com.harish.library.service.impl.CSVParser;

/**
 * 
 * @author harishsc
 *
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class CSVParserTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@InjectMocks
	CSVParser parser;

	@Test
	public void testParseBookDtoFromFile_Success() throws IOException {
		// Given
		//String fileContent = "\"\",\"444-2-96-948111-0\",\"Harry Potter\",1,\"[\"fun\",\"fantasy\"]";
		String fileContent = ",isbn,title,authorId,tags".concat("\n").concat(",444-2-96-948111-0,Harry Potter,1,[fun]");
		MockMultipartFile file = mock(MockMultipartFile.class);
		InputStream stream = new ByteArrayInputStream(fileContent.getBytes(Charset.forName("UTF-8")));
		when(file.getInputStream()).thenReturn(stream);

		// When
		List<BookRequestDto> bookList = parser.parseBookDtoFromFile(file);

		// Then
		assertEquals(1, bookList.size());
	}
	
	@Test
	public void testParseBookDtoFromFile_InvalidCSV_ThenThrowInvalidFileException() throws IOException {
		thrown.expect(InvalidFileException.class);
		// Given
		String fileContent = "\"\",\"444-2-96-948111-0\",\"Harry Potter\",1,\"[\"fun\",\"fantasy\"]";
		MockMultipartFile file = mock(MockMultipartFile.class);
		InputStream stream = new ByteArrayInputStream(fileContent.getBytes(Charset.forName("UTF-8")));
		when(file.getInputStream()).thenReturn(stream);

		// When
		parser.parseBookDtoFromFile(file);
	}
}
