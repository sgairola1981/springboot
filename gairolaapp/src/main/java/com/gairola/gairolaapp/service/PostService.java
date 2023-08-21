package com.gairola.gairolaapp.service;

import com.gairola.gairolaapp.entity.Paged;
import com.gairola.gairolaapp.entity.Post;

public interface PostService {
    public Paged<Post> getPage(int pageNumber, int size);

}
