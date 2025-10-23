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
public class GetLastPlayedMatchesDTO {
    private String homeTeam;
    private int goalsHomeTeam;
    private String awayTeam;
    private int goalsAwayTeam;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssZ")
    private OffsetDateTime matchDateTime;
}
