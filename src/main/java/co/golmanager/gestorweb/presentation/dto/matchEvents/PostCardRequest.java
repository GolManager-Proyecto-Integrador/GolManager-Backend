package co.golmanager.gestorweb.presentation.dto.matchEvents;

import co.golmanager.gestorweb.enums.CardColor;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCardRequest {
    @NotNull
    private Long matchId;
    @NotNull
    private Long playerId;
    @NotNull
    private int cardMinute;
    @NotNull
    private CardColor cardColor;
}
