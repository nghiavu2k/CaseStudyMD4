package casestudy.socialnetwork.repository;

import casestudy.socialnetwork.model.Post;
import casestudy.socialnetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Iterable<Post> findAllByAuthor(User user);
}
