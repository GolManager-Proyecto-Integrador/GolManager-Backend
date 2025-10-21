package co.golmanager.gestorweb.presentation.dto.teamPosition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPositionsTournamentDTO {
    private String teamName;
    private int points;
    private int gamesPlayed;
    private int gamesWon;
    private int gamesTied;
    private int gamesLost;
    private int goalsScored;
    private int goalsConceded;
    private int goalsDifference;
}
