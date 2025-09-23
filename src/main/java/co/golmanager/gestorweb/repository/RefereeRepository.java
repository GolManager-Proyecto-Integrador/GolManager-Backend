package co.golmanager.gestorweb.repository;

import co.golmanager.gestorweb.entity.Referee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefereeRepository extends  JpaRepository<Referee, Long> {
}
