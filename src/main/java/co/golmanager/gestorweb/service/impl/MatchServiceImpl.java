package co.golmanager.gestorweb.service.impl;

import co.golmanager.gestorweb.entity.Match;
import co.golmanager.gestorweb.presentation.dto.match.CreateMatchRequest;
import co.golmanager.gestorweb.presentation.dto.match.CreateMatchResponse;
import co.golmanager.gestorweb.repository.MatchRepository;
import co.golmanager.gestorweb.service.interfaces.*;
import co.golmanager.gestorweb.util.DateUtils;
import co.golmanager.gestorweb.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    private final UserService userService;
    private final TeamService teamService;
    private final TournamentService tournamentService;
    private final RefereeService refereeService;

    @Override
    public Match createMatch(Long tournamentId,CreateMatchRequest request, String email) {

        Match match = Match.builder()
                .homeTeam(teamService.getTeamById(tournamentId, email, request.getHomeTeamId()))
                .awayTeam(teamService.getTeamById(tournamentId, email, request.getAwayTeamId()))
                .tournament(tournamentService.getTournamentById(email, request.getTournamentId()))
                .stadium(request.getStadiumName())
                .referee(refereeService.getReferee(request.getReferee()))
                .matchDate(DateUtils.LocalToOffsetDate(request.getMatchDate()))
                .build();

        Match savedMatch = matchRepository.save(match);

        return savedMatch;
    }

    @Override
    public CreateMatchResponse createMatchResponse(Long tournamentId, CreateMatchRequest request, String email) {
        Long userId = userService.getIdByEmail(email);
        ValidationUtils.idAuthorizationValidation(tournamentId,userId);
        Match match = createMatch(tournamentId, request, email);
        return CreateMatchResponse.builder()
                .matchId(match.getId())
                .homeTeam(match.getHomeTeam().getName())
                .awayTeam(match.getAwayTeam().getName())
                .matchDate(match.getMatchDate())
                .stadiumName((match.getStadium()))
                .build();
    }
}
