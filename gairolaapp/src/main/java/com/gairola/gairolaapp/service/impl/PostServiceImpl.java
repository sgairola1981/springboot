package com.gairola.gairolaapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.gairola.gairolaapp.entity.Paged;
import com.gairola.gairolaapp.entity.Paging;
import com.gairola.gairolaapp.entity.Post;
import com.gairola.gairolaapp.repository.PostRepository;
import com.gairola.gairolaapp.repository.UserInfoRepository;
import com.gairola.gairolaapp.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository repo;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {

        this.repo = postRepository;
    }

    @Override
    public Paged<Post> getPage(int pageNumber, int size) {

        PageRequest request = PageRequest.of(pageNumber - 1, size, new Sort((List<Order>) Sort.by("ID")));
        Page<Post> postPage = repo.findAll(request);
        return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), pageNumber, size));
    }

}
