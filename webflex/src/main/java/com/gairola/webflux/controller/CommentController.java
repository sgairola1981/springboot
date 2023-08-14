package com.gairola.webflux.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gairola.webflux.model.Comment;
import com.gairola.webflux.model.Tutorial;
import com.gairola.webflux.repository.CommentRepository;
import com.gairola.webflux.service.DataService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    DataService dataService;
    private Mono<List<Tutorial>> collectList;
    private List<Tutorial> collect;
    private List<Tutorial> block;
    private List<Tutorial> block2;

    @GetMapping(path = "/comment/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Comment> feed() {
        return this.commentRepository.findAll();
    }

    @GetMapping(value = "/findall", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<Tutorial> getTutorials() {
        // System.out.println("*******************************" + block.toString());
        block2 = dataService.getTutorials().collectList().block();
        block2.forEach(System.out::println);
        return dataService.getTutorials();
    }

    @GetMapping("/tutorialsData")
    public List<Tutorial> findAll() {
        System.out.println("*******************************");

        System.out.println(dataService.FindAllData().get(1).toString());

        return dataService.FindAllData();

    }

    @GetMapping("/AllData")
    public Flux<User> getAllUsers() {
        return dataService.getAllUsers();
    }

}