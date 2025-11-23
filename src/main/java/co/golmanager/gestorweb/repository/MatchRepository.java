package co.golmanager.gestorweb.repository;

import co.golmanager.gestorweb.entity.Match;
import co.golmanager.gestorweb.presentation.dto.match.GetLastPlayedMatchesDTO;
import co.golmanager.gestorweb.presentation.dto.match.GetMatchResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("""
                SELECT new co.golmanager.gestorweb.presentation.dto.match.GetLastPlayedMatchesDTO(
                    m.id,
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
                    m.id,
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


        @Query("""
                SELECT new co.golmanager.gestorweb.presentation.dto.match.GetMatchResponse(
                           m.tournament.id,
                           m.tournament.name,
                           m.id,
                           m.homeTeam.name,
                           m.homeTeam.id,
                           m.awayTeam.name,
                           m.awayTeam.id,
                           m.matchDate,
                           m.stadium,
                           m.homeGoals,
                           m.awayGoals,
                           m.referee.id,
                           m.referee.name
                           )
                FROM Match m
                LEFT JOIN m.referee ref
                WHERE m.tournament.user.id= :organizerId
                AND m.matchDate BETWEEN :initDate AND :toDate
                ORDER BY m.id DESC
                """)
        //Faltaban valores porque referee id estaba vacio, agregue LEFT JOIN y la consulta funciono correctamente
        List<GetMatchResponse> findAllMatchesByOrganizerId(
                @Param("organizerId") Long organizerId,
                @Param("initDate") OffsetDateTime initDate,
                @Param("toDate") OffsetDateTime toDate);

}
