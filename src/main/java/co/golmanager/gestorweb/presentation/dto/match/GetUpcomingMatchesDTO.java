package co.golmanager.gestorweb.presentation.dto.match;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUpcomingMatchesDTO {
    private String homeTeam;
    private String awayTeam;
    private OffsetDateTime matchDateTIme;
    private String stadium;
}
