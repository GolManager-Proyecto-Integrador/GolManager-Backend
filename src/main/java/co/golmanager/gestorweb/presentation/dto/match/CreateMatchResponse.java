package co.golmanager.gestorweb.presentation.dto.match;

import co.golmanager.gestorweb.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    private OffsetDateTime matchDate;

}
