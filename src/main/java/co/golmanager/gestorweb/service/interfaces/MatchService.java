package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.entity.Match;
import co.golmanager.gestorweb.presentation.dto.match.CreateMatchRequest;
import co.golmanager.gestorweb.presentation.dto.match.CreateMatchResponse;
import co.golmanager.gestorweb.presentation.dto.match.GetLastPlayedMatchesResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface MatchService {
    Match createMatch(Long tournamentId, CreateMatchRequest request, String email);
    CreateMatchResponse createMatchResponse(Long tournamentId, CreateMatchRequest request, String email);
    GetLastPlayedMatchesResponse getLastPlayedMatches(Long tournamentId, int numberRegisters, String email);
    GetLastPlayedMatchesResponse getUpcomingMatches(Long tournamentId, int numberRegisters, String email);
}
