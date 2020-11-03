package casestudy.socialnetwork.repository;


import casestudy.socialnetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
