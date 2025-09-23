package co.golmanager.gestorweb.controller.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TournamentDeleteResponse {
    private Long tournamentId;
    private String name;
    private LocalDateTime deletionDate;
}
