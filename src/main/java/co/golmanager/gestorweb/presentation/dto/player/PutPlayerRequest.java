package co.golmanager.gestorweb.presentation.dto.player;

import co.golmanager.gestorweb.enums.PlayerPosition;
import co.golmanager.gestorweb.enums.PlayerStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PutPlayerRequest {
    @NotNull
    private Long idPlayer;
    @NotBlank
    private String name;
    @NotNull
    private PlayerPosition position;
    @NotNull
    private boolean starter;
    @NotNull
    private int shirtNumber;
    @NotNull
    private PlayerStatus status;
}
