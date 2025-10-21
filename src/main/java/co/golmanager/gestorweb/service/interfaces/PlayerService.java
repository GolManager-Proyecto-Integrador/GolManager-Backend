package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.presentation.dto.player.CreatePlayerRequest;
import co.golmanager.gestorweb.entity.Player;
import co.golmanager.gestorweb.entity.Team;
import co.golmanager.gestorweb.presentation.dto.player.ScorerPlayersResponse;
import co.golmanager.gestorweb.presentation.dto.player.SuspendedPlayersResponse;
import org.springframework.stereotype.Service;

@Service
public interface PlayerService {
    Player createPlayer(CreatePlayerRequest createPlayerRequest, Team team);
    SuspendedPlayersResponse getSuspendedPlayers(String status, Long tournamentId, String email);
    ScorerPlayersResponse getScorerPlayers(Long tournamentId, String email, int numberRegisters);
}
