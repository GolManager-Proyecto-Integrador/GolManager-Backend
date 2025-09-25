package co.golmanager.gestorweb.controller.dto.requests;

import co.golmanager.gestorweb.enums.PlayerPosition;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlayerRequest {
    @NotNull
    private String name;
    @NotNull
    private int age;
    @NotNull
    private PlayerPosition playerPosition;
    @NotNull
    private int shirtNumber;
}
