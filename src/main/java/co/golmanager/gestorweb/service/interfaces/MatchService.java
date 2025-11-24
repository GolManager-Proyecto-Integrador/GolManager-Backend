package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.entity.Match;
import co.golmanager.gestorweb.presentation.dto.generalDto.GeneralDeleteResponse;
import co.golmanager.gestorweb.presentation.dto.match.CreateMatchRequest;
import co.golmanager.gestorweb.presentation.dto.match.CreateMatchResponse;
import co.golmanager.gestorweb.presentation.dto.match.GetLastPlayedMatchesResponse;
import co.golmanager.gestorweb.presentation.dto.match.GetMatchResponse;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public interface MatchService {
    Match createMatch(Long tournamentId, CreateMatchRequest request, String email);
    CreateMatchResponse createMatchResponse(Long tournamentId, CreateMatchRequest request, String email);
    GetLastPlayedMatchesResponse getLastPlayedMatches(Long tournamentId, int numberRegisters, String email);
    GetLastPlayedMatchesResponse getUpcomingMatches(Long tournamentId, int numberRegisters, String email);
    List<GetMatchResponse> generateLeagueMatches(Long tournamentId, String email);
    GetMatchResponse getMatchById(Long matchId, Long tournamentId);
    Match matchById(Long matchId, Long tournamentId);
    GetMatchResponse editMatch(Long matchId, Long tournamentId, OffsetDateTime matchDate, String stadium, Long refereeId);
    GeneralDeleteResponse deleteMatch(Long matchId, Long tournamentId);
}
