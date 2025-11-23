package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.entity.Card;
import co.golmanager.gestorweb.entity.Goal;
import co.golmanager.gestorweb.enums.CardColor;
import co.golmanager.gestorweb.presentation.dto.generalDto.GeneralDeleteResponse;
import co.golmanager.gestorweb.presentation.dto.matchEvents.GetAllEventsForMatchResponse;
import co.golmanager.gestorweb.presentation.dto.matchEvents.GetCardDetailsResponse;
import co.golmanager.gestorweb.presentation.dto.matchEvents.GetGoalDetailsResponse;
import org.springframework.stereotype.Service;

@Service
public interface MatchEventService {
    //Goal Methods
    Goal recordGoal(Long tournamentId, Long matchId, Long playerId, Integer minute);
    GetGoalDetailsResponse getGoalDetails(Goal goal);
    Goal getGoal(Long tournamentId, Long goalId);
    Goal editGoal(Long tournamentId, Long matchId, Long playerId, Integer minute, Long  goalId);
    GeneralDeleteResponse deleteGoal(Long tournamentId, Long goalId);

    //Card Methods
    Card recordCard(Long tournamentId, Long matchId, Long playerId, Integer minute, CardColor cardColor);
    GetCardDetailsResponse getCardDetails(Card card);
    Card getCard(Long tournamentId, Long cardId);
    Card editCard(Long tournamentId, Long matchId, Long playerId, Integer minute, CardColor cardColor, Long cardId);
    GeneralDeleteResponse deleteCard(Long tournamentId, Long cardId);

    GetAllEventsForMatchResponse getAllEventsForMatch(Long tournamentId, Long matchId);
}
