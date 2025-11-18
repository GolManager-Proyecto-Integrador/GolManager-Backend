package co.golmanager.gestorweb.repository;


import co.golmanager.gestorweb.entity.Tournament;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    List<Tournament> findByUserId(@NonNull Long id);
    Optional<Tournament> findById(@NonNull Long id);
    int countAllBy();

    //Número total de torneos de un organizador
    @Query("""
           SELECT COUNT(t)
           FROM Tournament t
           WHERE t.user.id = :organizerId
           """)
    int countTournamentsByOrganizer(@Param("organizerId") Long organizerId);

    //Torneos creados en los últimos días de un organizador
    @Query("""
           SELECT COUNT(t)
           FROM Tournament t
           WHERE t.user.id = :organizerId
           AND t.dateCreated >= :fromDate
           """)
    int countTournamentsCreatedSince(@Param("organizerId") Long organizerId, @Param("fromDate") OffsetDateTime fromDate);

    // Torneos en progreso (con hoy entre starDate y endDate)
    @Query("""
           SELECT COUNT(t)
           FROM Tournament t
           WHERE t.user.id = :organizerId
           AND :today BETWEEN t.startDate AND t.endDate
           """)
    int countTournamentsInProgress(@Param("organizerId") Long organizerId, @Param("today") LocalDate today);
}
