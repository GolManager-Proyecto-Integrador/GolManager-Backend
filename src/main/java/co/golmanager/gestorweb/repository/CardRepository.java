package co.golmanager.gestorweb.repository;

import co.golmanager.gestorweb.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByMatchId(Long matchId);
}
