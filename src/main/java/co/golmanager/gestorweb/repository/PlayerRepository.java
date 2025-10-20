package co.golmanager.gestorweb.repository;

import co.golmanager.gestorweb.entity.Player;
import co.golmanager.gestorweb.enums.PlayerStatus;
import co.golmanager.gestorweb.presentation.dto.player.SuspendedPlayerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface    PlayerRepository extends JpaRepository<Player, Long> {
    @Query("""
        SELECT new co.golmanager.gestorweb.presentation.dto.player.SuspendedPlayerDTO(
            p.id,
            p.name,
            p.team.name,
            p.yellowCards
        )
        FROM Player p
        WHERE p.status = :status
        AND p.team.tournament.id = :tournamentId
    """)
    List<SuspendedPlayerDTO> findPlayersByStatusAndTournament(
            @Param("status") PlayerStatus status,
            @Param("tournamentId") Long tournamentId);
}

