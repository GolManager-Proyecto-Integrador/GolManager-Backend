package co.golmanager.gestorweb.repository;

import co.golmanager.gestorweb.entity.User;
import co.golmanager.gestorweb.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    int countByRole(Role role);

}
