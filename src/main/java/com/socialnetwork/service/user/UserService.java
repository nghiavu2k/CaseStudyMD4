package com.socialnetwork.service.user;


import com.socialnetwork.model.User;

import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findById(Long id);
    Iterable<User> findAll();
    User getCurrentUser();
    User getUserByUsername(String username);
}
