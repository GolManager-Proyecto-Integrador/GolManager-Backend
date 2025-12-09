package co.golmanager.gestorweb.service.impl;

import co.golmanager.gestorweb.entity.Tournament;
import co.golmanager.gestorweb.presentation.dto.generalDto.GeneralDeleteResponse;
import co.golmanager.gestorweb.presentation.dto.team.*;
import co.golmanager.gestorweb.entity.Team;
import co.golmanager.gestorweb.repository.TeamRepository;
import co.golmanager.gestorweb.service.interfaces.PlayerService;
import co.golmanager.gestorweb.service.interfaces.TeamPositionService;
import co.golmanager.gestorweb.service.interfaces.TeamService;
import co.golmanager.gestorweb.service.interfaces.TournamentService;
import co.golmanager.gestorweb.util.ValidationUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
class TeamServiceImpl implements TeamService {


    private final TournamentService tournamentService;
    private final PlayerService playerService;
    private final TeamPositionService teamPositionService;
    private final TeamRepository teamRepository;


    @Override
    @Transactional
    public Team createTeam(CreateTeamRequest request, String email, Long tournamentId) {

        Team team = Team.builder()
                .name(request.getTeamName())
                .coach(request.getCoachName())
                .category(request.getTeamCategory())
                .mainStadium(request.getMainStadium())
                .secondaryStadium(request.getSecondaryStadium())
                .dateCreated(OffsetDateTime.now())
                .tournament(tournamentService.getTournamentById(email, tournamentId))
                .build();

        Team savedTeam = teamRepository.save(team);

        //Crear los jugadores asociados al equipo
        request.getTeamPlayers().stream()
                .map(player -> playerService.createPlayer(player, savedTeam))
                .toList();
        //Crear tabla de posiciones
        teamPositionService.createTeamPosition(
                tournamentService.getTournamentById(email, tournamentId), savedTeam);

        return savedTeam;
    }

    @Override
    @Transactional
    public CreateTeamResponse createTeamResponse(CreateTeamRequest request, String email, Long tournamentId) {
        Team team = createTeam(request,email,tournamentId);
        return CreateTeamResponse.builder().message("Equipo "+ team.getName() +" creado con exito con el id: " + team.getId()).build();
    }

    @Override
    @Transactional
    public List<Team> getAllTeamsByTournament(Long tournamentId) {
        return teamRepository.findByTournament_id(tournamentId);
    }

    @Override
    @Transactional
    public List<GetTeamsTournamentSummaryResponse> getTeamsTournamentResponse(Long tournamentId, String email) {
        List<Team> teams = getAllTeamsByTournament(tournamentId);
       return teams.stream().map(t -> GetTeamsTournamentSummaryResponse.builder()
                       .idTeam(t.getId())
                       .teamName(t.getName())
                        .coachName(t.getCoach())
                        .mainStadium(t.getMainStadium())
                        .teamCategory(t.getCategory())
                        .build())
             .toList();
    }
    @Transactional
    @Override
    public Team getTeamById(Long tournamentId, String email, Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with id: " + teamId));
        Tournament tournament =  tournamentService.getTournamentById(email,tournamentId);
        ValidationUtils.idAuthorizationValidation(tournament.getId(),team.getTournament().getId());

        return team;
    }

    @Transactional
    @Override
    public TeamDetailsResponse getTeamDetailsResponse(Long tournamentId, String email, Long teamId) {
        Team team = getTeamById(tournamentId,email,teamId);
        return TeamDetailsResponse.builder()
                .teamId(team.getId())
                .name(team.getName())
                .coach(team.getCoach())
                .category(team.getCategory())
                .mainStadium(team.getMainStadium())
                .secondaryStadium(team.getSecondaryStadium())
                .dateCreated(OffsetDateTime.now())
                .build();
    }

    @Transactional
    @Override
    public Team updateTeam(Long teamId, Long tournamentId, UpdateTeamRequest request, String email) {
        Team team = getTeamById(tournamentId, email, teamId);

        team.setName(request.getName());
        team.setCoach(request.getCoach());
        team.setCategory(request.getTeamCategory());
        team.setMainStadium(request.getMainStadium());
        team.setSecondaryStadium(request.getSecondaryStadium());

        Team savedTeam = teamRepository.save(team);
        log.info("Team with the id {} was updated", savedTeam.getId());
        return savedTeam;

    }

    @Transactional
    @Override
    public TeamDetailsResponse updateTeamResponse(Long teamId, Long tournamentId, UpdateTeamRequest request, String email) {
        Team team = updateTeam(teamId, tournamentId, request, email);
        return TeamDetailsResponse.builder()
                .teamId(team.getId())
                .name(team.getName())
                .coach(team.getCoach())
                .category(team.getCategory())
                .mainStadium(team.getMainStadium())
                .secondaryStadium(team.getSecondaryStadium())
                .dateCreated(OffsetDateTime.now())
                .build();
    }

    @Transactional
    @Override
    public GeneralDeleteResponse deleteTeam(Long teamId, Long tournamentId, String email) {
        Team team = getTeamById(tournamentId, email, teamId);
        String teamName = team.getName();
        OffsetDateTime deleteDateTime = OffsetDateTime.now();
        teamRepository.delete(team);
        log.info("Team {} with the id {} was removed", teamName, teamId);

        return GeneralDeleteResponse.builder()
                .elementId(teamId)
                .elementName(teamName)
                .deletionElementDate(deleteDateTime)
                .build();
    }
}
