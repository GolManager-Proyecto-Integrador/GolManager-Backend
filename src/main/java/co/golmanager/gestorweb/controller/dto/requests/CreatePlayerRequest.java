package co.golmanager.gestorweb.controller.dto.requests;

import co.golmanager.gestorweb.enums.PlayerPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlayerRequest {
    private String name;
    private int age;
    private PlayerPosition playerPosition;
    private int shirtNumber;
}
