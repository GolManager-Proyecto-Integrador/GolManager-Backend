package co.golmanager.gestorweb.presentation.dto.tournament;

import co.golmanager.gestorweb.enums.TournamentFormat;
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
public class TournamentDetailResponse {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private TournamentFormat format;
    private boolean homeAndAway;
    private int numberOfTeams;
    private int yellowCardsSuspension;
    private List<Long> refereeIds;
}
