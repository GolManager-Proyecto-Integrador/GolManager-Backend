package co.golmanager.gestorweb.presentation.dto.organizer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetDashboardOrganizerResponse {
    private String userName;
    private int numTournaments;
    private int numTournamentsCreateThisMonth;
    private int numTournamentsInProgress;
    private int numMatchesThisWeek;
    private int numTeamsRegistered;
}
