package com.harish.library.service;

import com.harish.library.dto.AuthorRequestDto;
import com.harish.library.model.Author;

public interface IAuthorStoreService {
	Author addAuthor(AuthorRequestDto authorRequestDto);
}
