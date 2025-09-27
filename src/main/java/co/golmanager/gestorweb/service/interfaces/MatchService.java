package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.entity.Match;
import co.golmanager.gestorweb.presentation.dto.match.CreateMatchRequest;
import co.golmanager.gestorweb.presentation.dto.match.CreateMatchResponse;
import org.springframework.stereotype.Service;

@Service
public interface MatchService {
    public Match createMatch(Long tournamentId, CreateMatchRequest request, String email);
    public CreateMatchResponse createMatchResponse(Long tournamentId, CreateMatchRequest request, String email);
}
