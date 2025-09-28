package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.presentation.dto.tournament.CreateTournamentRequest;
import co.golmanager.gestorweb.presentation.dto.tournament.CreateTournamentResponse;
import co.golmanager.gestorweb.presentation.dto.tournament.TournamentDeleteResponse;
import co.golmanager.gestorweb.presentation.dto.tournament.TournamentDetailResponse;
import co.golmanager.gestorweb.presentation.dto.tournament.TournamentSummaryResponse;
import co.golmanager.gestorweb.entity.Tournament;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TournamentService {

    List<TournamentSummaryResponse> getAllTournamentsById(String email);
    Tournament getTournamentById(String email, Long tournamentId);
    TournamentDetailResponse getTournamentResponseById(String tournamentId, String email);

    CreateTournamentResponse createTournament
            (CreateTournamentRequest request, String email);

    TournamentDetailResponse updateTournament
            (Long tournamentId, CreateTournamentRequest request, String email);

    TournamentDeleteResponse deleteTournament(Long tournamentId, String email);
}
