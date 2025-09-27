package co.golmanager.gestorweb.presentation.dto.tournament;

import co.golmanager.gestorweb.enums.TournamentFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TournamentSummaryResponse {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int numberOfTeams;
    private TournamentFormat format;
}
