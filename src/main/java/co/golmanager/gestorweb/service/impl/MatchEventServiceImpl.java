package co.golmanager.gestorweb.service.impl;


import co.golmanager.gestorweb.entity.Goal;
import co.golmanager.gestorweb.entity.Match;
import co.golmanager.gestorweb.entity.Player;
import co.golmanager.gestorweb.presentation.dto.generalDto.GeneralDeleteResponse;
import co.golmanager.gestorweb.presentation.dto.matchEvents.GetGoalDetailsResponse;
import co.golmanager.gestorweb.repository.GoalRepository;
import co.golmanager.gestorweb.repository.MatchRepository;
import co.golmanager.gestorweb.service.interfaces.MatchEventService;
import co.golmanager.gestorweb.service.interfaces.MatchService;
import co.golmanager.gestorweb.service.interfaces.PlayerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchEventServiceImpl implements MatchEventService {

    private final PlayerService playerService;
    private MatchService matchService;
    private MatchRepository matchRepository;
    private GoalRepository goalRepository;

    @Override
    @Transactional
    public Goal recordGoal(Long tournamentId, Long matchId, Long playerId, Integer minute) {

        Match match = matchService.matchById(matchId,  tournamentId);
        Player player = playerService.getPlayer(tournamentId, playerId);

        Goal g = Goal.builder()
                .match(match)
                .player(player)
                .minute(minute)
                .build();

        log.info("New goal record with id {}", g.getId());
        return goalRepository.save(g);
    }

    @Override
    public GetGoalDetailsResponse getGoalDetails(Goal savedGoal) {
        return  GetGoalDetailsResponse.builder()
                .goalId(savedGoal.getId())
                .matchId((long) savedGoal.getMatch().getId())
                .matchDate(savedGoal.getMatch().getMatchDate())
                .playerId(savedGoal.getPlayer().getId())
                .playerTeamId(savedGoal.getPlayer().getTeam().getId())
                .playerTeamName(savedGoal.getPlayer().getTeam().getName())
                .minute(savedGoal.getMinute())
                .build();
    }

    @Override
    @Transactional
    public Goal getGoal(Long tournamentId, Long goalId) {

        Optional<Goal> g = goalRepository.findById(goalId);

        if(g.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found");
        }

        if (!Objects.equals(g.get().getMatch().getTournament().getId(), tournamentId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Goal with id: " + goalId + " does not belong to the tournament");
        }


        log.info("Details of goal  with id {} consulted.", goalId);
        return g.get();
    }

    @Override
    @Transactional
    public Goal editGoal(Long tournamentId, Long matchId, Long playerId, Integer minute, Long  goalId) {

        Goal goal = getGoal(tournamentId, goalId);
        Match match = matchService.matchById(matchId,  tournamentId);
        Player player = playerService.getPlayer(tournamentId, playerId);

        goal.setMatch(match);
        goal.setPlayer(player);
        goal.setMinute(minute);

        log.info("Goal  with id {} edited", goal.getId());

        return goalRepository.save(goal);
    }

    @Override
    @Transactional
    public GeneralDeleteResponse deleteGoal(Long tournamentId, Long goalId) {

        Goal goal =  getGoal(tournamentId, goalId);
        Long logGoalId = goal.getId();
        goalRepository.delete(goal);
        OffsetDateTime deleteDate = OffsetDateTime.now();
        log.info("Goal  with id {} deleted", logGoalId);

        return GeneralDeleteResponse.builder()
                .elementName("Goal")
                .elementId(logGoalId)
                .deletionElementDate(deleteDate)
                .build();
    }
}
