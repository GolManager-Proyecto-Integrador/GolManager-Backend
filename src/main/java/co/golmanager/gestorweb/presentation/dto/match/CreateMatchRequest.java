package co.golmanager.gestorweb.presentation.dto.match;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMatchRequest {
    @NotNull
    private Long homeTeamId;
    @NotNull
    private Long awayTeamId;
    @NotNull
    private Long tournamentId;
    private String stadiumName;
    private Long referee;
    private OffsetDateTime matchDate;
}
