package co.golmanager.gestorweb.repository;

import co.golmanager.gestorweb.entity.User;
import co.golmanager.gestorweb.enums.Role;
import co.golmanager.gestorweb.presentation.dto.admin.OrganizersResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    int countByRole(Role role);
    Optional<User> findAllByRole(Role role);

    @Query("""
        SELECT new co.golmanager.gestorweb.presentation.dto.admin.OrganizersResponse
            (
            u.id,
            u.name,
            u.email,
            COUNT(t)
        )
        FROM User u
        LEFT JOIN u.tournaments t
        WHERE u.role = :role
        GROUP BY u.id, u.name, u.email
    """)
    List<OrganizersResponse> findOrganizersWithTournamentCount(@Param("role") Role role);

}
