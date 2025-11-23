package co.golmanager.gestorweb.presentation.dto.matchEvents;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostGoalRequest {

    @NotNull
    private Long matchId;
    @NotNull
    private Long playerId;
    @NotNull
    private int goalMinute;

}
