package com.gairola.gairolaapp.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gairola.gairolaapp.entity.Book;
import com.gairola.gairolaapp.service.BookService;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private RestTemplate restTemplate;
    private List<Book> asList;

    @Override
    public List<Book> findAllBooks() {
        System.out.println("*************************************");
        Book[] books = restTemplate.getForObject("http://localhost:9000/book/all", Book[].class);

        System.out.println("*************************************");

        for (Book e : books) {
            System.out.println(e);

        }
        return asList;
    }

}
