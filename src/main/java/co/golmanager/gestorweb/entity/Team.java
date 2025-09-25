package co.golmanager.gestorweb.entity;

import co.golmanager.gestorweb.enums.TeamCategory;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "tbl_team")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Team {
    // Atributos
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String coach;
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column( columnDefinition = "team_category")
    private TeamCategory category;
    @Column(name = "main_stadium")
    private String mainStadium;
    @Column(name = "secondary_stadium")
    private String secondaryStadium;
    @Column(name = "date_created", columnDefinition = "timestamptz")
    private OffsetDateTime dateCreated;

    @ManyToOne(targetEntity = Tournament.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournamentId;

    @OneToOne(mappedBy = "team", targetEntity = TeamPosition.class, fetch = FetchType.LAZY)
    private TeamPosition teamPosition;

    @OneToMany(mappedBy = "team", targetEntity = Player.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<Player> players;

    @OneToMany(mappedBy = "homeTeam", targetEntity = Match.class, fetch = FetchType.LAZY)
    private java.util.List<Match> homeMatches;

   @OneToMany(mappedBy = "awayTeam", targetEntity = Match.class, fetch = FetchType.LAZY)
    private java.util.List<Match> awayMatches;

}
