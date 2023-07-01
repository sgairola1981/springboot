package com.gairola.webflux.repository;

import com.gairola.webflux.model.Comment;

import reactor.core.publisher.Flux;

public interface CommentRepository {

    Flux<Comment> findAll();

}