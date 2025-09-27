package co.golmanager.gestorweb.presentation.dto.match;



import co.golmanager.gestorweb.entity.Tournament;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMatchRequest {
    private Long homeTeamId;
    private Long awayTeamId;
    private Long tournamentId;
    private String stadiumName;
    private Long referee;
    private LocalDate matchDate;
}
