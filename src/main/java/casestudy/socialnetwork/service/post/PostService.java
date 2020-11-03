package casestudy.socialnetwork.service.post;

import casestudy.socialnetwork.model.Post;
import casestudy.socialnetwork.model.User;

import java.util.Optional;

public interface PostService {
    Iterable<Post> findAll();
    Post save(Post post);
    void remove(Long id);
    Optional<Post> findById(Long id);
    Iterable<Post> findAllByAuthor(User author);
}
