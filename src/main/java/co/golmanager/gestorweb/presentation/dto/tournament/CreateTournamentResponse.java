package co.golmanager.gestorweb.presentation.dto.tournament;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTournamentResponse {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String format;
    private boolean homeAndAway;
    private int numberOfTeams;
    private int yellowCardsSuspension;
    private List<Long> refereeIds;
}
