package co.golmanager.gestorweb.presentation.dto.team;

import co.golmanager.gestorweb.enums.TeamCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTeamsTournamentResponse {
    private String teamName;
    private String coachName;
    private String mainStadium;
    private TeamCategory teamCategory;
}
