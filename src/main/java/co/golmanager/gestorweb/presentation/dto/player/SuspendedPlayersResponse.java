package co.golmanager.gestorweb.presentation.dto.player;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuspendedPlayersResponse {
    private List<SuspendedPlayerDTO> players;
}
