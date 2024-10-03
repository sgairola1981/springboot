package com.gairola.gairolaapp.service;

import java.util.List;

import com.gairola.gairolaapp.entity.Book;

public interface BookService {

    public List<Book> findAllBooks();

    public Book findBooks(String id);

}
