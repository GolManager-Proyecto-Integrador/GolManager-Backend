package co.golmanager.gestorweb.repository;


import co.golmanager.gestorweb.entity.Tournament;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    List<Tournament> findByUserId(@NonNull Long id);
    Optional<Tournament> findById(@NonNull Long id);
    int countAllBy();
}
