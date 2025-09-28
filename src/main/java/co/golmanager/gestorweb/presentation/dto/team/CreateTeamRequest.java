package co.golmanager.gestorweb.presentation.dto.team;

import co.golmanager.gestorweb.enums.TeamCategory;
import co.golmanager.gestorweb.presentation.dto.player.CreatePlayerRequest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTeamRequest {
    @NotNull
    private String name;
    private String coach;
    @NotNull
    private TeamCategory teamCategory;
    @NotNull
    private String mainStadium;
    @NotNull
    private String secondaryStadium;
    @NotNull
    private List<CreatePlayerRequest> teamPlayers;
}
