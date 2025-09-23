package co.golmanager.gestorweb.entity;


import co.golmanager.gestorweb.enums.CardColor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_card")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int minute;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "card_type")
    private CardColor color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private Match match;

}
