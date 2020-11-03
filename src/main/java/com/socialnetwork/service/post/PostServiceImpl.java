package com.socialnetwork.service.post;

import com.socialnetwork.model.Post;
import com.socialnetwork.model.User;
import com.socialnetwork.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PostServiceImpl implements PostService{
    @Autowired
    PostRepository postRepository;

    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void remove(Post post) {
        postRepository.delete(post);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Iterable<Post> findAllByAuthor(User author) {
        return postRepository.findAllByAuthor(author);
    }
}
