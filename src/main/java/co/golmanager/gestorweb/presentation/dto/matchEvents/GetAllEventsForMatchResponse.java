package co.golmanager.gestorweb.presentation.dto.matchEvents;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllEventsForMatchResponse {
    private List<GetGoalDetailsResponse> listGoals;
    private List<GetCardDetailsResponse> listCards;

}
