package co.golmanager.gestorweb.service.impl;

import co.golmanager.gestorweb.entity.Match;
import co.golmanager.gestorweb.presentation.dto.match.CreateMatchRequest;
import co.golmanager.gestorweb.presentation.dto.match.CreateMatchResponse;
import co.golmanager.gestorweb.presentation.dto.match.GetLastPlayedMatchesDTO;
import co.golmanager.gestorweb.presentation.dto.match.GetLastPlayedMatchesResponse;
import co.golmanager.gestorweb.repository.MatchRepository;
import co.golmanager.gestorweb.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MatchServiceImpl implements MatchService {


    private final MatchRepository matchRepository;

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
                .matchDate(request.getMatchDate())
                .build();

        Match savedMatch = matchRepository.save(match);
        log.info("Created Match with ID: {}", savedMatch.getId());
        return savedMatch;
    }

    @Override
    public CreateMatchResponse createMatchResponse(Long tournamentId, CreateMatchRequest request, String email) {
        log.info("Attempt create a Match for tournament with ID: {}", tournamentId);
        tournamentService.getTournamentById(email,tournamentId);
        Match match = createMatch(tournamentId, request, email);
        log.info("Time match created with ID: {}, {}", match.getId(), match.getMatchDate());
        return CreateMatchResponse.builder()
                .matchId(match.getId())
                .homeTeam(match.getHomeTeam().getName())
                .awayTeam(match.getAwayTeam().getName())
                .matchDate(match.getMatchDate())
                .stadiumName((match.getStadium()))
                .build();
    }

    @Override
    public GetLastPlayedMatchesResponse getLastPlayedMatches(Long tournamentId, int numberRegisters, String email) {
        tournamentService.getTournamentById(email,tournamentId);
        OffsetDateTime currentDateTime = OffsetDateTime.now();
        Pageable  pageable = PageRequest.of(0, numberRegisters);
        List<GetLastPlayedMatchesDTO> matches = matchRepository.findLastPlayedMatchesByTournamentId(tournamentId, currentDateTime, pageable);
        log.info("List of matches played in the tournament with ID {} obtained correctly", tournamentId);
        return GetLastPlayedMatchesResponse.builder()
                .matches(matches)
                .build();
    }

    @Override
    public GetLastPlayedMatchesResponse getUpcomingMatches(Long tournamentId, int numberRegisters, String email) {
        tournamentService.getTournamentById(email,tournamentId);
        OffsetDateTime currentDateTime = OffsetDateTime.now();

        Pageable  pageable = PageRequest.of(0, numberRegisters);
        List<GetLastPlayedMatchesDTO> matches = matchRepository.findUpcomingMatchesByTournamentId(tournamentId, currentDateTime, pageable);
        log.info("List of matches upcoming in the tournament with ID {} obtained correctly", tournamentId);
        return GetLastPlayedMatchesResponse.builder()
                .matches(matches)
                .build();
    }
}
