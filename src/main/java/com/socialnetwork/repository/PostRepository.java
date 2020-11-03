package com.socialnetwork.repository;

import com.socialnetwork.model.Post;
import com.socialnetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Iterable<Post> findAllByAuthor(User user);
}
