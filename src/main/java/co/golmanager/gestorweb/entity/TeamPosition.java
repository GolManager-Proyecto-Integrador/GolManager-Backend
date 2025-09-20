package co.golmanager.gestorweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_team_position")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamPosition {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int points;
    @Column(name = "goals_scored")
    private int goalsScored;
    @Column(name = "goals_against")
    private int goalsAgainst;
    @Column(name = "matches_played")
    private int matchesPlayed;
    @Column(name = "matches_won")
    private int matchesWon;
    @Column(name = "matches_lost")
    private int matchesLost;
    @Column(name = "matches_tied")
    private int matchesTied;

    @ManyToOne(targetEntity = Tournament.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @OneToOne(targetEntity = Team.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

}
