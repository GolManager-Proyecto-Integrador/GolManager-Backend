package co.golmanager.gestorweb.repository;

import co.golmanager.gestorweb.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByTournament_id(Long tournamentId);
    Optional<Team> findById(Long id);
    int countAllBy();

    @Query("""
           SELECT COUNT(t)
           FROM Team t
           WHERE t.tournament.user.id = :organizerId
           """)
    int countTeamByOrganizer(@Param("organizerId") Long organizerId);
}
