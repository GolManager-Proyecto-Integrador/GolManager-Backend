package co.golmanager.gestorweb.presentation.dto.player;
import co.golmanager.gestorweb.enums.PlayerPosition;
import co.golmanager.gestorweb.enums.PlayerStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPlayerDTOResponse {
    private Long idPlayer;
    private String name;
    private PlayerPosition position;
    private boolean starter;
    private int shirtNumber;
    private int goals;
    private int yellowCards;
    private int redCards;
    private PlayerStatus status;
}
