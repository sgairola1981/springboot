package com.gairola.webflux.service;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gairola.webflux.model.Tutorial;

import reactor.core.publisher.Flux;

@Service
public class DataService {
    @Autowired
    WebClient webClient;

    public Flux<Tutorial> getTutorials() {

        return webClient.get()
                .uri("/api/tutorials")
                .retrieve()
                .bodyToFlux(Tutorial.class)
                .timeout(Duration.ofMillis(10_000));

    }

    public List<Tutorial> FindAllData() {

        return webClient.get()
                .uri("/api/tutorials")
                .retrieve()
                .bodyToFlux(Tutorial.class)
                .collectList()
                .block();
    }

    public Flux<User> getAllUsers() {
        return null;
    }

}
