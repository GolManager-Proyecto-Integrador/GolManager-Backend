package co.golmanager.gestorweb.presentation.dto.matchEvents;

import co.golmanager.gestorweb.enums.CardColor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCardDetailsResponse {
    private Long cardId;
    private Long matchId;
    private CardColor cardColor;
    private OffsetDateTime matchDate;
    private Long playerId;
    private Long playerTeamId;
    private String playerTeamName;
    private int minute;
}


