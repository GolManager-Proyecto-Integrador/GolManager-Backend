package co.golmanager.gestorweb.presentation.dto.match;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
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
    private String tournamentName;
    @NotNull
    private int matchId;
    private String homeTeam;
    @NotNull
    private Long homeTeamId;
    private String awayTeam;
    private Long awayTeamId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssZ")
    private OffsetDateTime matchDateTIme;
    private String stadium;
    private int goalsHomeTeam;
    private int goalsAwayTeam;
    private Long refereeId;
    private String refereeName;

}
