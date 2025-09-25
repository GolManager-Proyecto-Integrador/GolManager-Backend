package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.controller.dto.requests.CreateTournamentRequest;
import co.golmanager.gestorweb.controller.dto.responses.CreateTournamentResponse;
import co.golmanager.gestorweb.controller.dto.responses.TournamentDeleteResponse;
import co.golmanager.gestorweb.controller.dto.responses.TournamentDetailResponse;
import co.golmanager.gestorweb.controller.dto.responses.TournamentSummaryResponse;
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
