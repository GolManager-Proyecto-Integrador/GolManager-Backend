package co.golmanager.gestorweb.entity;

import co.golmanager.gestorweb.enums.TournamentFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_tournament")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tournament {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "start_date", columnDefinition = "DATE")
    private LocalDate startDate;
    @Column(name = "end_date", columnDefinition = "DATE")
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "tournament_format")
    private TournamentFormat format;
    @Column(name = "home_away")
    private boolean homeAndAway;
    @Column(name = "yellow_cards_suspension")
    private int yellowCardsSuspension;

    @ManyToOne (targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "tournament", targetEntity = Team.class, fetch = FetchType.LAZY)
    private java.util.List<Team> teams;

    @OneToMany(mappedBy = "tournament", targetEntity = TeamPosition.class, fetch = FetchType.LAZY)
    private java.util.List<TeamPosition> teamPositions;

    @OneToMany(mappedBy = "tournament", targetEntity = Match.class, fetch = FetchType.LAZY)
    private java.util.List<Match> matches;

    @ManyToMany(targetEntity = Referee.class,fetch = FetchType.LAZY)
    private java.util.List<Referee> referees;
}
