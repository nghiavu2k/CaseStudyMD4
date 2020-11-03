package casestudy.socialnetwork.service.user;


import casestudy.socialnetwork.model.User;

import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findById(Long id);
    Iterable<User> findAll();
    User getCurrentUser();
    User getUserByUsername(String username);
}
