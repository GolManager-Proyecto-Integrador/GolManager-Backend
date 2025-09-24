package co.golmanager.gestorweb.entity;

import co.golmanager.gestorweb.enums.MatchWinner;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "tbl_match")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Match {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String stadium;
    @Column(name = "match_date", columnDefinition = "timestamptz")
    private OffsetDateTime matchDate;
    @Column(name = "home_goals")
    private int homeGoals;
    @Column(name = "away_goals")
    private int awayGoals;
    @Enumerated(EnumType.STRING)
    @Column(name = "winner", columnDefinition = "match_winner")
    private MatchWinner winner;

    // Relación con el equipo local
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_home_id", nullable = false)
    private Team homeTeam;

    // Relación con el equipo visitante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_away_id", nullable = false)
    private Team awayTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @ManyToOne(targetEntity = Referee.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "referee_id")
    private Referee referee;

    @OneToMany(mappedBy = "match", targetEntity = Goal.class, fetch = FetchType.LAZY)
    private java.util.List<Goal> goals;

    @OneToMany(mappedBy = "match", targetEntity = Card.class, fetch = FetchType.LAZY)
    private java.util.List<Card> cards;
}
