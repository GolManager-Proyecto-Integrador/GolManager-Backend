package co.golmanager.gestorweb.presentation.dto.tournament;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TournamentDeleteResponse {
    private Long tournamentId;
    private String name;
    private OffsetDateTime deletionDate;
}
