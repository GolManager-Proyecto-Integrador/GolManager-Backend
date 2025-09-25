package co.golmanager.gestorweb.service.impl;

import co.golmanager.gestorweb.controller.dto.requests.CreateTeamRequest;
import co.golmanager.gestorweb.controller.dto.responses.CreateTeamResponse;
import co.golmanager.gestorweb.entity.Player;
import co.golmanager.gestorweb.entity.Team;
import co.golmanager.gestorweb.repository.PlayerRepository;
import co.golmanager.gestorweb.repository.TeamRepository;
import co.golmanager.gestorweb.service.interfaces.TeamService;
import co.golmanager.gestorweb.service.interfaces.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
class TeamServiceImp implements TeamService {

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;

    @Override
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

//        List<Player> players = request.getTeamPlayers().stream()
//                .map(playerReq -> Player.builder()
//                        .name(playerReq.getName())
//                        .position(playerReq.getPlayerPosition())
//                        .shirtNumber(playerReq.getShirtNumber())
//                        .age(playerReq.getAge())
//                        .team(savedTeam) // vincular al equipo
//                        .build())
//                .toList();
//
//        playerRepository.saveAll(players);
//
//        // 4. Retornar el equipo con jugadores
//        savedTeam.setPlayers(players);
        return savedTeam;
    }

    @Override
    public CreateTeamResponse createTeamResponse(Team team) {
        return CreateTeamResponse.builder().message("Equipo "+ team.getName() +"creado con exito con el id: " + team.getId()).build();
    }
}
