package co.golmanager.gestorweb.presentation.dto.player;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScorerPlayerDTO {
    private String playerName;
    private String team;
    private int goalScore;
}
