package co.golmanager.gestorweb.service;

import co.golmanager.gestorweb.controller.models.requests.CreateTournamentRequest;
import co.golmanager.gestorweb.controller.models.responses.CreateTournamentResponse;
import co.golmanager.gestorweb.controller.models.responses.TournamentDeleteResponse;
import co.golmanager.gestorweb.controller.models.responses.TournamentDetailResponse;
import co.golmanager.gestorweb.controller.models.responses.TournamentSummaryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TournamentService {

    List<TournamentSummaryResponse> getAllTournamentsById(String email);
    TournamentDetailResponse getTournamentById(String tournamentId, String email);

    CreateTournamentResponse createTournament
            (CreateTournamentRequest request, String email);

    TournamentDetailResponse updateTournament
            (Long tournamentId, CreateTournamentRequest request, String email);

    TournamentDeleteResponse deleteTournament(Long tournamentId, String email);
}
