package co.golmanager.gestorweb.repository;

import co.golmanager.gestorweb.entity.TeamPosition;
import co.golmanager.gestorweb.presentation.dto.teamPosition.GetPositionsTournamentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamPositionRepository extends JpaRepository<TeamPosition, Long> {
    @Query("""
        SELECT new co.golmanager.gestorweb.presentation.dto.teamPosition.GetPositionsTournamentDTO(
            tp.team.id,
            tp.team.name,
            tp.points,
            tp.matchesPlayed,
            tp.matchesWon,
            tp.matchesTied,
            tp.matchesLost,
            tp.goalsScored,
            tp.goalsAgainst,
            (tp.goalsScored - tp.goalsAgainst)
        )
        FROM TeamPosition tp
        WHERE tp.tournament.id = :tournamentId
        ORDER BY tp.points DESC, (tp.goalsScored - tp.goalsAgainst) DESC, tp.goalsScored
    """)
    List<GetPositionsTournamentDTO> findPositionsByTournamentId(@Param("tournamentId") Long tournamentId);}
