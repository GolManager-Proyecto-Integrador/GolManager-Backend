package co.golmanager.gestorweb.presentation.dto.match;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMatchResponse {
    private int matchId;
    private String homeTeam;
    private String awayTeam;
    private String stadiumName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssZ")
    private OffsetDateTime matchDate;
    private Long tournamentId;

}
