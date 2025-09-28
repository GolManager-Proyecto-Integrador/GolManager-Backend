package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.presentation.dto.generalDto.GeneralDeleteResponse;
import co.golmanager.gestorweb.presentation.dto.team.*;
import co.golmanager.gestorweb.entity.Team;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamService {
    Team createTeam(CreateTeamRequest request, String email, Long tournamentId);
    CreateTeamResponse createTeamResponse(CreateTeamRequest request, String email, Long tournamentId);

    List<Team> getAllTeamsByTournament(Long tournamentId);
    List<GetTeamsTournamentSummaryResponse> getTeamsTournamentResponse(Long tournamentId, String email);

    Team getTeamById(Long tournamentId, String email, Long teamId);

    TeamDetailsResponse getTeamDetailsResponse(Long tournamentId, String email, Long teamId);

    Team updateTeam(Long teamId, Long tournamentId, UpdateTeamRequest request, String email);
    TeamDetailsResponse updateTeamResponse(Long teamId, Long tournamentId, @Valid UpdateTeamRequest request, String email);

    GeneralDeleteResponse deleteTeam(Long teamId, Long tournamentId, String email);

}
