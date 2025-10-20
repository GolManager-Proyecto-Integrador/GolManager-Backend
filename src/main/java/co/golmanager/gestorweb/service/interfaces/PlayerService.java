package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.enums.PlayerStatus;
import co.golmanager.gestorweb.presentation.dto.player.CreatePlayerRequest;
import co.golmanager.gestorweb.entity.Player;
import co.golmanager.gestorweb.entity.Team;
import co.golmanager.gestorweb.presentation.dto.player.SuspendedPlayerDTO;
import co.golmanager.gestorweb.presentation.dto.player.SuspendedPlayersResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerService {
    Player createPlayer(CreatePlayerRequest createPlayerRequest, Team team);
    SuspendedPlayersResponse getSuspendedPlayers(String status, Long tournamentId, String email);
}
