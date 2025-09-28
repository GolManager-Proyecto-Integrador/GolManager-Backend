package co.golmanager.gestorweb.repository;

import co.golmanager.gestorweb.entity.TeamPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamPositionRepository extends JpaRepository<TeamPosition, Long> {
}
