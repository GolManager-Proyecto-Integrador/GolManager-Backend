package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.presentation.dto.team.CreateTeamRequest;
import co.golmanager.gestorweb.presentation.dto.team.CreateTeamResponse;
import co.golmanager.gestorweb.entity.Team;
import co.golmanager.gestorweb.presentation.dto.team.GetTeamsTournamentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamService {
    public Team createTeam(CreateTeamRequest request, String email, Long tournamentId);
    public CreateTeamResponse createTeamResponse(CreateTeamRequest request, String email, Long tournamentId);

    public List<Team> getAllTeamsByTournament(Long tournamentId);
    public List<GetTeamsTournamentResponse> getTeamsTournamentResponse(Long tournamentId, String email);
}
