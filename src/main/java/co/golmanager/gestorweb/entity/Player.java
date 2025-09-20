package co.golmanager.gestorweb.entity;

import co.golmanager.gestorweb.enums.PlayerPosition;
import co.golmanager.gestorweb.enums.PlayerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_player")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Player {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "player_position")
    private PlayerPosition position;
    private boolean starter; // true si es titular, false si es suplente
    @Column(name = "shirt_number")
    private int shirtNumber;
    private int goals;
    private int assists;
    @Column(name = "yellow_cards")
    private int yellowCards;
    @Column(name = "red_cards")
    private int redCards;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "player_status")
    private PlayerStatus status;

    @ManyToOne(targetEntity = Team.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @OneToMany(mappedBy = "player", targetEntity = Goal.class, fetch = FetchType.LAZY)
    private java.util.List<Goal> goalsList;

    @OneToMany(mappedBy = "player", targetEntity = Card.class, fetch = FetchType.LAZY)
    private java.util.List<Card> cards;
}
