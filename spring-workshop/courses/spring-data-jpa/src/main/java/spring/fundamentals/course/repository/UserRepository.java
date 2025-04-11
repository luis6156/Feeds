package spring.fundamentals.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.fundamentals.course.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // from User where email=:email
    User findUserByEmail(String email);
}
