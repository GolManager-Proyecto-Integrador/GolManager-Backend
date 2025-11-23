package co.golmanager.gestorweb.presentation.dto.matchEvents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetGoalDetailsResponse {

    private Long goalId;
    private Long matchId;
    private OffsetDateTime matchDate;
    private Long playerId;
    private Long playerTeamId;
    private String playerTeamName;
    private int minute;

}
