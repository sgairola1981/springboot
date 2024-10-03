package com.gairola.gairolaapp.service.impl;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.gairola.gairolaapp.entity.Book;
import com.gairola.gairolaapp.service.BookService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class BookServiceImpl implements BookService {
    // @Value("${api.catalog.baseurl}")
    private String serviceUrl = "http://localhost:9000";

    @Autowired
    private RestTemplate restTemplate;

    private List<Book> asList;
    private Map<String, Book> cache = new HashMap<>();

    @Override
    public List<Book> findAllBooks() {
        System.out.println("*************************************");
        Book[] books = restTemplate.getForObject("http://localhost:9000/book/all", Book[].class);

        System.out.println("*************************************");

        for (Book e : books) {
            System.out.println(e);
            // asList.add(Book e);

        }
        return asList;
    }

    @Override
    @CircuitBreaker(name = "bookservice", fallbackMethod = "getProductFallbackn")
    public Book findBooks(String id) {
        // String url = serviceUrl + "{bookId}";
        String url = "http://localhost:9000/book/" + id;
        // Book book = restTemplate.getForObject(URI.create(url), Book.class, id);
        System.out.println("**************findBooks ***********************");
        Book book = restTemplate.getForObject(URI.create(url), Book.class);
        System.out.println("*************************************" + book.getBookName());
        cache.put(id, book);
        return book;
    }

    public Book getProductFallbackn(int productId, CallNotPermittedException exception) {
        System.out.println("*************************************" + exception.getMessage());
        Book book = new Book();
        String bookid = "100";
        book.setBookId(100);
        book.setBookName("Testing");
        book.setBookCost(200.33);
        cache.put(bookid, book);
        return book;
    }

}
