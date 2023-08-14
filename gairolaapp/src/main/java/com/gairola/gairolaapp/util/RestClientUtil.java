package com.gairola.gairolaapp.util;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.gairola.gairolaapp.entity.Book;

@Component
public class RestClientUtil {

    public void getAllBooks() throws URISyntaxException {
        URI uri = new URI("http://localhost:9000/book/all");

        RestTemplate restTemplate = new RestTemplate();
        Book[] books = restTemplate.getForObject(uri, Book[].class);

        for (Book e : books) {
            System.out.println(e);
        }
    }

}
