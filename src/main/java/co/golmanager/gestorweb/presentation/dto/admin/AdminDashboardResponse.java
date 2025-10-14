package co.golmanager.gestorweb.presentation.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDashboardResponse {
    String userName;
    int numOrganizers;
    int numTournaments;
    int numTeams;
}
