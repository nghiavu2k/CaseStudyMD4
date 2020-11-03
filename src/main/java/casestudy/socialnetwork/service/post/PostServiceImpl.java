package casestudy.socialnetwork.service.post;

import casestudy.socialnetwork.model.Post;
import casestudy.socialnetwork.model.User;
import casestudy.socialnetwork.repository.PostRepository;
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
    public void remove(Long id) {
        postRepository.deleteById(id);
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
