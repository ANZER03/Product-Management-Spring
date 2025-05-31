package ma.enset.productmangmentspring.repo;

import ma.enset.productmangmentspring.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
