package co.golmanager.gestorweb.repository;

import co.golmanager.gestorweb.entity.Match;
import co.golmanager.gestorweb.presentation.dto.match.GetLastPlayedMatchesDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("""
        SELECT new co.golmanager.gestorweb.presentation.dto.match.GetLastPlayedMatchesDTO(
            m.homeTeam.name,
            m.homeGoals,
            m.awayTeam.name,
            m.awayGoals,
            m.matchDate
        )
        FROM Match m
        WHERE m.tournament.id = :tournamentId AND m.matchDate < :currentDate
        ORDER BY m.matchDate DESC
    """)
    List<GetLastPlayedMatchesDTO> findLastPlayedMatchesByTournamentId(@Param("tournamentId") Long tournamentId, @Param("currentDate") OffsetDateTime currentDate, Pageable pageable);

    @Query("""
        SELECT new co.golmanager.gestorweb.presentation.dto.match.GetLastPlayedMatchesDTO(
            m.homeTeam.name,
            m.homeGoals,
            m.awayTeam.name,
            m.awayGoals,
            m.matchDate
        )
        FROM Match m
        WHERE m.tournament.id = :tournamentId AND m.matchDate > :currentDate
        ORDER BY m.matchDate ASC
    """)
    List<GetLastPlayedMatchesDTO> findUpcomingMatchesByTournamentId(@Param("tournamentId") Long tournamentId, @Param("currentDate") OffsetDateTime currentDate, Pageable pageable);

    //Número de Partidos en una semana por usuario

    @Query("""
           SELECT COUNT(m)
           FROM Match m
           WHERE m.tournament.user.id= :organizerId
           AND m.matchDate BETWEEN :fromDate AND :toDate
           """)
    int countMatchInRange(@Param("organizerId") Long organizerId, @Param("fromDate") OffsetDateTime fromDate, @Param("toDate") OffsetDateTime toDate);

}
