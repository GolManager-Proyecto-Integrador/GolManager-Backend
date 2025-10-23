package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.entity.Team;
import co.golmanager.gestorweb.entity.TeamPosition;
import co.golmanager.gestorweb.entity.Tournament;
import co.golmanager.gestorweb.presentation.dto.teamPosition.GetPositionsTournamentResponse;
import org.springframework.stereotype.Service;

@Service
public interface TeamPositionService {
    TeamPosition createTeamPosition(Tournament tournament, Team team);
    GetPositionsTournamentResponse getPositionsTournament(Long tournamentId, String email);
}
