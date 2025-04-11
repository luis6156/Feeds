package spring.fundamentals.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.fundamentals.course.model.Profile;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
	Optional<Profile> findByUserId(int userId); // Find profile by associated user ID
}
