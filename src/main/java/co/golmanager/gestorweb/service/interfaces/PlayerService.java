package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.presentation.dto.player.*;
import co.golmanager.gestorweb.entity.Player;
import co.golmanager.gestorweb.entity.Team;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerService {
    Player createPlayer(CreatePlayerRequest createPlayerRequest, Team team);
    SuspendedPlayersResponse getSuspendedPlayers(String status, Long tournamentId, String email);
    ScorerPlayersResponse getScorerPlayers(Long tournamentId, String email, int numberRegisters);
    YellowCardPlayersResponse getYellowCardPlayers(Long tournamentId, String email,  int numberRegisters);
    List<GetPlayerDTOResponse> getPlayersByTournamentIdAndTeam(Long tournamentId, Long idTeam, String email);
    GetPlayerDTOResponse modifyPlayerInfo(Long tournamentId, PutPlayerRequest request, String email);

}
