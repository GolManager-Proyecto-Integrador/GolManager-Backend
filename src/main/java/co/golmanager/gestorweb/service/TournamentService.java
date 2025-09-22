package co.golmanager.gestorweb.service;

import co.golmanager.gestorweb.controller.models.requests.CreateTournamentRequest;
import co.golmanager.gestorweb.controller.models.responses.CreateTournamentResponse;
import org.springframework.stereotype.Service;

@Service
public interface TournamentService {
    CreateTournamentResponse createTournament
            (CreateTournamentRequest request, String email);
}
