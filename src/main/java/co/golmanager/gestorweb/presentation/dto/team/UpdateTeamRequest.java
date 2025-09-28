package co.golmanager.gestorweb.presentation.dto.team;

import co.golmanager.gestorweb.enums.TeamCategory;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTeamRequest {
    @NotNull
    private String name;
    @NotNull
    private String coach;
    @NotNull
    private TeamCategory teamCategory;
    @NotNull
    private String mainStadium;
    @NotNull
    private String secondaryStadium;
}
