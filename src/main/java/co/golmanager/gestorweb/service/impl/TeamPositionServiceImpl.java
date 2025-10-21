package co.golmanager.gestorweb.service.impl;

import co.golmanager.gestorweb.entity.Team;
import co.golmanager.gestorweb.entity.TeamPosition;
import co.golmanager.gestorweb.entity.Tournament;
import co.golmanager.gestorweb.presentation.dto.teamPosition.GetPositionsTournamentDTO;
import co.golmanager.gestorweb.presentation.dto.teamPosition.GetPositionsTournamentResponse;
import co.golmanager.gestorweb.repository.TeamPositionRepository;
import co.golmanager.gestorweb.service.interfaces.TeamPositionService;
import co.golmanager.gestorweb.service.interfaces.TournamentService;
import co.golmanager.gestorweb.service.interfaces.UserService;
import co.golmanager.gestorweb.util.ValidationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamPositionServiceImpl implements TeamPositionService {

    private final TeamPositionRepository teamPositionRepository;
    private final UserService userService;
    private final TournamentService tournamentService;

    @Override
    @Transactional
    public TeamPosition createTeamPosition(Tournament tournament, Team team) {

        TeamPosition teamPosition = TeamPosition.builder()
                .team(team)
                .tournament(tournament)
                .goalsAgainst(0)
                .goalsScored(0)
                .matchesLost(0)
                .matchesWon(0)
                .matchesPlayed(0)
                .matchesTied(0)
                .points(0)
                .build();
        TeamPosition teamPositionSaved = teamPositionRepository.save(teamPosition);
        log.info("TeamPosition created for team: {}", teamPositionSaved.getTeam().getName());

        return teamPositionSaved;
    }

    @Override
    @Transactional
    public GetPositionsTournamentResponse getPositionsTournament(Long tournamentId, String email) {
        log.info("Position query for tournament with id: {}", tournamentId);
        tournamentService.getTournamentById(email, tournamentId);
        List<GetPositionsTournamentDTO> positions = teamPositionRepository.findPositionsByTournamentId(tournamentId);

        return GetPositionsTournamentResponse.builder()
                .positions(positions)
                .build();
    }
}
