package com.socialnetwork.service.post;

import com.socialnetwork.model.Post;
import com.socialnetwork.model.User;

import java.util.Optional;

public interface PostService {
    Iterable<Post> findAll();
    Post save(Post post);
    void remove(Post post);
    Optional<Post> findById(Long id);
    Iterable<Post> findAllByAuthor(User author);
}
