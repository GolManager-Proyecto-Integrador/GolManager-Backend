package co.golmanager.gestorweb.service.impl;

import co.golmanager.gestorweb.controller.dto.requests.CreateTeamRequest;
import co.golmanager.gestorweb.controller.dto.responses.CreateTeamResponse;
import co.golmanager.gestorweb.entity.Player;
import co.golmanager.gestorweb.entity.Team;
import co.golmanager.gestorweb.repository.TeamRepository;
import co.golmanager.gestorweb.service.interfaces.PlayerService;
import co.golmanager.gestorweb.service.interfaces.TeamPositionService;
import co.golmanager.gestorweb.service.interfaces.TeamService;
import co.golmanager.gestorweb.service.interfaces.TournamentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
class TeamServiceImp implements TeamService {

    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private TeamPositionService teamPositionService;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    @Transactional
    public Team createTeam(CreateTeamRequest request, String email, Long tournamentId) {

        Team team = Team.builder()
                .name(request.getName())
                .coach(request.getCoach())
                .category(request.getTeamCategory())
                .mainStadium(request.getMainStadium())
                .secondaryStadium(request.getSecondaryStadium())
                .dateCreated(OffsetDateTime.now())
                .tournamentId(tournamentService.getTournamentById(email, tournamentId))
                .build();

        Team savedTeam = teamRepository.save(team);

        //Crear los jugadores asociados al equipo
        List<Player> players = request.getTeamPlayers().stream()
                .map(player -> playerService.createPlayer(player, savedTeam))
                .toList();
        //Crear tabla de posiciones
        teamPositionService.createTeamPosition(
                tournamentService.getTournamentById(email, tournamentId), savedTeam);

        return savedTeam;
    }

    @Override
    public CreateTeamResponse createTeamResponse(Team team) {
        return CreateTeamResponse.builder().message("Equipo "+ team.getName() +" creado con exito con el id: " + team.getId()).build();
    }
}
