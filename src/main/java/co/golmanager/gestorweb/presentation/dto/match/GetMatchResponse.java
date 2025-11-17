package co.golmanager.gestorweb.presentation.dto.match;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class GetMatchResponse {
    @NotNull
    private Long tournamentId;
    @NotNull
    private Long matchId;
    private String homeTeam;
    @NotNull
    private Long homeTeamId;
    private String awayTeam;
    @NotNull
    private Long awayTeamId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssZ")
    private OffsetDateTime matchDateTIme;
    private String stadium;
    private int goalsHomeTeam;
    private int goalsAwayTeam;
    private Long refereeId;

}
