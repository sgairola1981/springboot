package com.gairola.gairolaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gairola.gairolaapp.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
