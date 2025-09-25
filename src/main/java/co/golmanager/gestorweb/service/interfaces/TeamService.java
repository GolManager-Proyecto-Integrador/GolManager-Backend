package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.controller.dto.requests.CreateTeamRequest;
import co.golmanager.gestorweb.controller.dto.responses.CreateTeamResponse;
import co.golmanager.gestorweb.entity.Team;
import org.springframework.stereotype.Service;

@Service
public interface TeamService {
    public Team createTeam(CreateTeamRequest request, String email, Long tournamentId);
    public CreateTeamResponse createTeamResponse(Team team);

}
