package co.golmanager.gestorweb.presentation.dto.player;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YellowCardPlayerDTO {
    private String playerName;
    private String team;
    private int yellowCards;
}
