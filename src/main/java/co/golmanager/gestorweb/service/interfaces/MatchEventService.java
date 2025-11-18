package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.entity.Goal;
import co.golmanager.gestorweb.presentation.dto.generalDto.GeneralDeleteResponse;
import co.golmanager.gestorweb.presentation.dto.matchEvents.GetGoalDetailsResponse;
import org.springframework.stereotype.Service;

@Service
public interface MatchEventService {
    Goal recordGoal(Long tournamentId, Long matchId, Long playerId, Integer minute);
    GetGoalDetailsResponse getGoalDetails(Goal goal);
    Goal getGoal(Long tournamentId, Long goalId);
    Goal editGoal(Long tournamentId, Long matchId, Long playerId, Integer minute, Long  goalId);
    GeneralDeleteResponse deleteGoal(Long tournamentId, Long goalId);
}
